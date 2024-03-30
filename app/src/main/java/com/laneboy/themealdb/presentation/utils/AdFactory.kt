package com.laneboy.themealdb.presentation.utils

import com.laneboy.themealdb.R
import javax.inject.Inject

class AdFactory @Inject constructor() {

    private val adList = listOf(
        AdItem(R.drawable.ic_ad_1),
        AdItem(R.drawable.ic_ad_2),
        AdItem(R.drawable.ic_ad_3),
        AdItem(R.drawable.ic_ad_4),
        AdItem(R.drawable.ic_ad_5)
    )

    fun createAds() : List<AdItem> {
        return adList
    }
}