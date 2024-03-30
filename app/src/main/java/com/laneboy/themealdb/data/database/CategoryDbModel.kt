package com.laneboy.themealdb.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_list")
data class CategoryDbModel(
    @PrimaryKey
    val name: String
)