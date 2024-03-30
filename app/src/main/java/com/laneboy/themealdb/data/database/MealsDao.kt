package com.laneboy.themealdb.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MealsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setCategories(categories: List<CategoryDbModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setMealsDbModels(mealsDbModels: List<MealsDbModel>)

    @Query("SELECT * FROM meal_list WHERE category = :category")
    fun getMeals(category: String): List<MealsDbModel>

    @Query("SELECT * FROM category_list")
    fun getCategories(): List<CategoryDbModel>
}
