package com.hgshkt.androidtask5.api.model

import com.google.gson.annotations.SerializedName

data class SuperHero(
    val name: String,
    val images: Images,
    @SerializedName("powerstats")
    val powerStats: PowerStats?,
    val biography: Biography?
)
