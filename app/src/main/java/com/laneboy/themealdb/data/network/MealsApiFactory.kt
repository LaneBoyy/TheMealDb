package com.laneboy.themealdb.data.network

import com.google.gson.GsonBuilder
import com.laneboy.themealdb.BuildConfig
import com.laneboy.themealdb.data.wrapper.NetworkResponseAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object MealsApiFactory {

    private val httpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofitSources = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .client(okHttpClient)
        .build()

    val mealsApiService: MealsApiService = retrofitSources.create()
}