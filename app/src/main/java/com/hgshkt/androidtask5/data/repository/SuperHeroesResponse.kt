package com.hgshkt.androidtask5.data.repository

import com.hgshkt.androidtask5.data.repository.model.SuperHero

data class SuperHeroesResponse(
    val body: List<SuperHero>?,
    val isSuccessful: Boolean,
    val errorMassage: String?
)
