package com.laneboy.themealdb.presentation.main_menu.meal_list_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.laneboy.themealdb.databinding.MealItemBinding

import com.laneboy.themealdb.domain.models.MealItem

class MealAdapter : ListAdapter<MealItem, MealItemViewHolder>(MealDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealItemViewHolder {
        return MealItemViewHolder(
            MealItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MealItemViewHolder, position: Int) {
        val mealItem = getItem(position)
        holder.bind(mealItem)
    }
}