package com.laneboy.themealdb.presentation.main_menu.ad_list_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.laneboy.themealdb.databinding.AdItemBinding
import com.laneboy.themealdb.presentation.utils.AdItem

class AdAdapter : ListAdapter<AdItem, AdItemViewHolder>(AdDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdItemViewHolder {
        return AdItemViewHolder(
            AdItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdItemViewHolder, position: Int) {
        val adItem = getItem(position)
        holder.bind(adItem)
    }
}