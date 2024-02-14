package com.hgshkt.androidtask5.api

import com.hgshkt.androidtask5.api.model.SuperHero
import retrofit2.http.GET

interface ApiInterface {
    @GET("/superhero-api/api/all.json")
    suspend fun getSuperHeroes(): List<SuperHero>
}