package com.laneboy.themealdb.data

import com.laneboy.themealdb.data.database.MealsDao
import com.laneboy.themealdb.data.mappers.ResponseToDomainMapper
import com.laneboy.themealdb.data.network.MealsApiService
import com.laneboy.themealdb.data.wrapper.ApiErrorResponse
import com.laneboy.themealdb.data.wrapper.ApiSuccessResponse
import com.laneboy.themealdb.domain.MealsRepository
import com.laneboy.themealdb.domain.models.MealCategory
import com.laneboy.themealdb.domain.models.MealItem
import com.laneboy.themealdb.domain.models.Resource
import javax.inject.Inject

class MealsRepositoryImpl @Inject constructor(
    private val mealsApiService: MealsApiService,
    private val mealsDao: MealsDao,
    private val mapper: ResponseToDomainMapper
) : MealsRepository {

    override suspend fun getMeals(category: String): Resource<List<MealItem>> =
        when (val resource = mealsApiService.getMeals(category = category)) {
            is ApiSuccessResponse -> {
                Resource.success(mapper.responseToListMealItemsDomain(resource.data.meals))
            }

            is ApiErrorResponse -> Resource.error(resource.error)
        }

    override suspend fun loadOrCacheCategory(): Resource<List<MealCategory>> {
        return when (val resource = mealsApiService.getAllFoodCategories()) {
            is ApiSuccessResponse -> {
                val result = resource.data.mealsCategory
                mealsDao.setCategories(mapper.responseToListCategoryDbModel(result))
                Resource.success(mapper.responseToListCategoryDomain(result))
            }

            is ApiErrorResponse -> {
                val list = mealsDao.getCategories()
                if (list.isNotEmpty()) {
                    Resource.success(mapper.dbModelToListCategoryDomain(list))
                } else {
                    Resource.error(resource.error)
                }
            }
        }
    }

    override suspend fun loadOrCacheMeals(category: String): Resource<List<MealItem>> {
        return when (val resource = mealsApiService.getMeals(category)) {
            is ApiSuccessResponse -> {
                val result = resource.data.meals
                mealsDao.setMealsDbModels(mapper.responseToListMealDbModel(result, category))
                Resource.success(mapper.responseToListMealItemsDomain(result))
            }
            is ApiErrorResponse -> {
                val meals = mealsDao.getMeals(category)
                if (meals.isNotEmpty()) {
                    Resource.success(mapper.dbModelToListMealsDomain(meals))
                } else {
                    Resource.error(resource.error)
                }
            }
        }
    }
}