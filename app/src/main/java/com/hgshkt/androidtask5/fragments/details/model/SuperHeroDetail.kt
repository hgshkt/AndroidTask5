package com.hgshkt.androidtask5.fragments.details.model

data class SuperHeroDetail(
    val imageUrl: String,
    val name: String,
    val powerStats: PowerStatsDetail?,
    val biography: BiographyDetail?
)
