package com.hgshkt.androidtask5.api

import com.hgshkt.androidtask5.api.model.SuperHero
import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {
    @GET("/superhero-api/api/all.json")
    fun getSuperHeroes(): Single<List<SuperHero>>
}