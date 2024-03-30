package com.laneboy.themealdb.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [CategoryDbModel::class, MealsDbModel::class],
    version = 1,
    exportSchema = false
)
abstract class MealsDatabase : RoomDatabase() {
    companion object {
        private var db: MealsDatabase? = null
        private const val DB_NAME = "meals.db"
        private val LOCK = Any()

        fun getInstance(context: Context): MealsDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context = context,
                        klass = MealsDatabase::class.java,
                        name = DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun mealsDao(): MealsDao
}