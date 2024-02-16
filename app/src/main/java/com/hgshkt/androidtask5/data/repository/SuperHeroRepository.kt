package com.hgshkt.androidtask5.data.repository

import com.hgshkt.androidtask5.data.repository.model.SuperHero

interface SuperHeroRepository {
    suspend fun getSuperHeroes(): List<SuperHero>
}