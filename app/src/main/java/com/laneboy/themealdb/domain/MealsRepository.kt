package com.laneboy.themealdb.domain

import com.laneboy.themealdb.domain.models.MealCategory
import com.laneboy.themealdb.domain.models.MealItem
import com.laneboy.themealdb.domain.models.Resource

interface MealsRepository {

    suspend fun getMeals(category: String): Resource<List<MealItem>>

    suspend fun loadOrCacheCategory(): Resource<List<MealCategory>>

    suspend fun loadOrCacheMeals(category: String): Resource<List<MealItem>>
}