package com.hgshkt.androidtask5.api

import com.hgshkt.androidtask5.api.model.SuperHeroesResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {
    @GET("/all.json")
    fun getSuperHeroes(): Single<SuperHeroesResponse>
}