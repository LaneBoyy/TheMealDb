package com.laneboy.themealdb.domain.usecases

import com.laneboy.themealdb.domain.MealsRepository
import com.laneboy.themealdb.domain.models.MealCategory
import com.laneboy.themealdb.domain.models.Resource
import javax.inject.Inject

class LoadOrCacheCategory @Inject constructor(
    private val repository: MealsRepository
) {

    suspend operator fun invoke(): Resource<List<MealCategory>> {
        return repository.loadOrCacheCategory()
    }
}