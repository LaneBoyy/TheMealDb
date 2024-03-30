package com.laneboy.themealdb.data.responses

import com.google.gson.annotations.SerializedName

data class MealItemResponse(
    @SerializedName("strMeal")
    val strMeal: String,
    @SerializedName("strMealThumb")
    val strMealThumb: String,
    @SerializedName("idMeal")
    val idMeal: String,
)
