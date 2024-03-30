package com.laneboy.themealdb

import android.app.Application
import com.laneboy.themealdb.di.DaggerApplicationComponent

class App : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}