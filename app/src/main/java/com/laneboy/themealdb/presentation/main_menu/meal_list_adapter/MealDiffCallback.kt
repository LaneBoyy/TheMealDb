package com.laneboy.themealdb.presentation.main_menu.meal_list_adapter

import androidx.recyclerview.widget.DiffUtil
import com.laneboy.themealdb.domain.models.MealItem

class MealDiffCallback : DiffUtil.ItemCallback<MealItem>() {

    override fun areItemsTheSame(oldItem: MealItem, newItem: MealItem): Boolean {
        return oldItem.mealId == newItem.mealId
    }

    override fun areContentsTheSame(oldItem: MealItem, newItem: MealItem): Boolean {
        return oldItem == newItem
    }
}