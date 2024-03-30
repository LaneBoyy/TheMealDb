package com.laneboy.themealdb.di

import android.app.Application
import com.laneboy.themealdb.data.MealsRepositoryImpl
import com.laneboy.themealdb.data.database.MealsDao
import com.laneboy.themealdb.data.database.MealsDatabase
import com.laneboy.themealdb.data.network.MealsApiFactory
import com.laneboy.themealdb.data.network.MealsApiService
import com.laneboy.themealdb.domain.MealsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @[ApplicationScope Binds]
    fun bindMealsRepository(impl: MealsRepositoryImpl): MealsRepository

    companion object {
        @[ApplicationScope Provides]
        fun provideMealsApiService(): MealsApiService = MealsApiFactory.mealsApiService

        @[ApplicationScope Provides]
        fun provideMealsDao(
            application: Application
        ): MealsDao {
            return MealsDatabase.getInstance(application).mealsDao()
        }
    }
}