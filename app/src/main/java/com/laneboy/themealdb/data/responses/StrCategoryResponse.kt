package com.laneboy.themealdb.data.responses

import com.google.gson.annotations.SerializedName

data class StrCategoryResponse(
    @SerializedName("strCategory")
    val strCategory: String
)