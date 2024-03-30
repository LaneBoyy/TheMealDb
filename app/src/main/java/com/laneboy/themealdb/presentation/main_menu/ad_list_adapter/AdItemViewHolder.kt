package com.laneboy.themealdb.presentation.main_menu.ad_list_adapter

import androidx.recyclerview.widget.RecyclerView
import com.laneboy.themealdb.databinding.AdItemBinding
import com.laneboy.themealdb.presentation.utils.AdItem

class AdItemViewHolder(
    private val binding: AdItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(adItem: AdItem) {
        binding.ivAd.setImageResource(adItem.adImageId)
    }
}