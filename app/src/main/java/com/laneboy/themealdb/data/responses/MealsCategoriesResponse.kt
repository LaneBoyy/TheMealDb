package com.laneboy.themealdb.data.responses

import com.google.gson.annotations.SerializedName

data class MealsCategoriesResponse(
    @SerializedName("meals")
    val mealsCategory: List<StrCategoryResponse>
)