package com.laneboy.themealdb.data.responses

import com.google.gson.annotations.SerializedName

data class MealsItemsResponse(
    @SerializedName("meals")
    val meals: List<MealItemResponse>
)
