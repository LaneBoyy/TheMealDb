package com.laneboy.themealdb.data.wrapper

import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.*

sealed class ApiResponse<T> {

    companion object {

        private const val HTTP_CODE_UNAUTHORIZED = 401
        private const val HTTP_CODE_FORBIDDEN = 403

        @Suppress("UNCHECKED_CAST", "Detekt.TooGenericExceptionCaught")
        fun <T> proceedResponse(response: Response<T>): ApiResponse<T> = try {
            if (response.isSuccessful) {
                ApiSuccessResponse(response.body()) as ApiResponse<T>
            } else {
                convertResponseToException(response)
            }
        } catch (th: Throwable) {
            createError(th)
        }

        private fun <T> convertResponseToException(response: Response<T>): ApiResponse<T> {
            return when (response.code()) {
                HTTP_CODE_UNAUTHORIZED -> createError(TokenInvalidException())
                HTTP_CODE_FORBIDDEN -> createError(TokenForbiddenException())
                else -> createError(ApiException(response.errorBody()?.string() ?: "Unknown error"))
            }
        }

        fun <T> createError(th: Throwable): ApiResponse<T> = when (th) {
            is ApiException -> ApiErrorResponse(th)
            is UnknownHostException, is SocketTimeoutException -> ApiErrorResponse(
                NoInternetException(Date().time)
            )

            else -> ApiErrorResponse(ApiException(message = th.message, cause = th))
        }
    }

    inline fun ifError(block: (error: ApiException) -> Unit): ApiResponse<T> {
        if (this is ApiErrorResponse<T>) {
            block(this.error)
        }
        return this
    }
}

data class ApiSuccessResponse<T>(val data: T) : ApiResponse<T>()
data class ApiErrorResponse<T>(val error: ApiException) : ApiResponse<T>()
