package com.laneboy.themealdb.presentation.main_menu.meal_list_adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.laneboy.themealdb.R
import com.laneboy.themealdb.databinding.MealItemBinding
import com.laneboy.themealdb.domain.models.MealItem
import kotlin.random.Random

class MealItemViewHolder(
    private val binding: MealItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(mealItem: MealItem) {
        Glide.with(binding.root.context)
            .load(mealItem.mealThumb)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_placeholder)
            .into(binding.ivMealImage)
        binding.tvTitle.text = mealItem.mealName

        val coast = Random.nextInt(199, 999)
        binding.btnCoast.text = String.format(
            binding.root.context.getString(R.string.coast_postfix),
            coast.toString()
        )
    }
}