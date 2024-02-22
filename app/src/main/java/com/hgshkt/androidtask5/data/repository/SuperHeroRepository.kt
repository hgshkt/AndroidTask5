package com.hgshkt.androidtask5.data.repository

interface SuperHeroRepository {
    suspend fun getSuperHeroes(): SuperHeroesResponse
}