package com.laneboy.themealdb.presentation.main_menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laneboy.themealdb.domain.models.MealCategory
import com.laneboy.themealdb.domain.models.MealItem
import com.laneboy.themealdb.domain.models.Resource
import com.laneboy.themealdb.domain.usecases.GetMealsUseCase
import com.laneboy.themealdb.domain.usecases.LoadOrCacheCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainMenuViewModel @Inject constructor(
    private val loadOrCacheCategory: LoadOrCacheCategory,
    private val getMealsUseCase: GetMealsUseCase
) : ViewModel() {

    private val _categories = MutableLiveData<List<MealCategory>>()
    val categories: LiveData<List<MealCategory>>
        get() = _categories

    private val _mealItems = MutableLiveData<Resource<List<MealItem>>>(Resource.loading())
    val mealItems: LiveData<Resource<List<MealItem>>>
        get() = _mealItems

    private var localCategories: List<String> = listOf()

    private var currentCategoryId: Int = 0

    private var repositoryJob: Job? = null

    init {
        loadCategoriesAndPage()
    }

    private fun loadCategoriesAndPage() {
        repositoryJob?.cancel()
        repositoryJob = viewModelScope.launch(Dispatchers.IO) {
            loadOrCacheCategory().ifSuccess { listOfCategories ->
                listOfCategories?.let {
                    _categories.postValue(it)
                    localCategories = it.map { mealCategory ->
                        mealCategory.categoryName
                    }
                }
            }.ifError {
                _mealItems.postValue(Resource.error(it))
            }
            currentCategoryId = 0
            if (localCategories.isNotEmpty()) {
                _mealItems.postValue(getMealsUseCase(localCategories[currentCategoryId]))
            }
        }
    }

    fun getMeals(categoryId: Int?) {
        repositoryJob?.cancel()
        repositoryJob = viewModelScope.launch(Dispatchers.IO) {
            categoryId?.let {
                currentCategoryId = categoryId
                _mealItems.postValue(getMealsUseCase(localCategories[categoryId]))
            }
        }

    }

    fun getCurrentCategoryId(): Int {
        return currentCategoryId
    }
}