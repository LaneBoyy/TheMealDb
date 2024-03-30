package com.laneboy.themealdb.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_list")
data class MealsDbModel (
    @PrimaryKey
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    val category: String
)