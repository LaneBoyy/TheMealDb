package com.laneboy.themealdb.data.mappers

import com.laneboy.themealdb.data.database.CategoryDbModel
import com.laneboy.themealdb.data.database.MealsDbModel
import com.laneboy.themealdb.data.responses.MealItemResponse
import com.laneboy.themealdb.data.responses.StrCategoryResponse
import com.laneboy.themealdb.domain.models.MealCategory
import com.laneboy.themealdb.domain.models.MealItem
import javax.inject.Inject

class ResponseToDomainMapper @Inject constructor() {

    fun dbModelToListMealsDomain(list: List<MealsDbModel>) = list.map {
        dbModelToMealItemDomain(it)
    }

    fun responseToListCategoryDomain(list: List<StrCategoryResponse>) = list.map {
        responseToCategoryDomain(it)
    }

    fun dbModelToListCategoryDomain(list: List<CategoryDbModel>) = list.map {
        dbModelToCategoryDomain(it)
    }

    fun responseToListMealItemsDomain(list: List<MealItemResponse>) = list.map {
        responseToMealItemDomain(it)
    }

    fun responseToListMealDbModel(list: List<MealItemResponse>, category: String) = list.map {
        responseToMealDbModel(it, category)
    }

    fun responseToListCategoryDbModel(list: List<StrCategoryResponse>) = list.map {
        responseToCategoryDbModel(it)
    }

    private fun responseToMealDbModel(mealItemResponse: MealItemResponse, category: String) =
        MealsDbModel(
            idMeal = mealItemResponse.idMeal,
            strMeal = mealItemResponse.strMeal,
            strMealThumb = mealItemResponse.strMealThumb,
            category = category
        )

    private fun responseToMealItemDomain(mealItemResponse: MealItemResponse) = MealItem(
        mealName = mealItemResponse.strMeal,
        mealThumb = mealItemResponse.strMealThumb,
        mealId = mealItemResponse.idMeal
    )

    private fun responseToCategoryDbModel(strCategoryResponse: StrCategoryResponse) =
        CategoryDbModel(
            name = strCategoryResponse.strCategory
        )

    private fun responseToCategoryDomain(strCategoryResponse: StrCategoryResponse) = MealCategory(
        categoryName = strCategoryResponse.strCategory
    )

    private fun dbModelToCategoryDomain(categoryDbModel: CategoryDbModel) = MealCategory(
        categoryName = categoryDbModel.name
    )

    private fun dbModelToMealItemDomain(dbModel: MealsDbModel) = MealItem(
        mealName = dbModel.strMeal,
        mealThumb = dbModel.strMealThumb,
        mealId = dbModel.idMeal
    )

}