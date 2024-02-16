package com.hgshkt.androidtask5.data.repository

import com.hgshkt.androidtask5.data.api.ApiInterface

class SuperHeroRepositoryImpl (
    private val api: ApiInterface
) : SuperHeroRepository {
    override suspend fun getSuperHeroes() = api.getSuperHeroes()
}