package com.laneboy.themealdb.presentation.main_menu.ad_list_adapter

import androidx.recyclerview.widget.DiffUtil
import com.laneboy.themealdb.presentation.utils.AdItem

class AdDiffCallback : DiffUtil.ItemCallback<AdItem>() {

    override fun areItemsTheSame(oldItem: AdItem, newItem: AdItem): Boolean {
        return oldItem.adImageId == newItem.adImageId
    }

    override fun areContentsTheSame(oldItem: AdItem, newItem: AdItem): Boolean {
        return oldItem == newItem
    }
}