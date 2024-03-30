package com.laneboy.themealdb.domain.usecases

import com.laneboy.themealdb.domain.MealsRepository
import com.laneboy.themealdb.domain.models.MealItem
import com.laneboy.themealdb.domain.models.Resource
import javax.inject.Inject

class GetMealsUseCase @Inject constructor(
    private val repository: MealsRepository
) {

    suspend operator fun invoke(category: String): Resource<List<MealItem>> {
        return repository.getMeals(category)
    }
}