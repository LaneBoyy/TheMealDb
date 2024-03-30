package com.laneboy.themealdb.data.network

import com.laneboy.themealdb.data.responses.MealsCategoriesResponse
import com.laneboy.themealdb.data.responses.MealsItemsResponse
import com.laneboy.themealdb.data.wrapper.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApiService {

    @GET("list.php?c=list")
    suspend fun getAllFoodCategories(): ApiResponse<MealsCategoriesResponse>

    @GET("filter.php?")
    suspend fun getMeals(
        @Query("c") category: String
    ): ApiResponse<MealsItemsResponse>
}