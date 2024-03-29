package com.hgshkt.androidtask5.data.api

import com.hgshkt.androidtask5.data.repository.model.SuperHero
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/superhero-api/api/all.json")
    suspend fun getSuperHeroes(): Response<List<SuperHero>>
}