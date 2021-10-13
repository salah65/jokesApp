package com.example.jokesapp.data.network

import com.example.jokesapp.data.network.dto.JokesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoints {

    @GET("/joke/Any")
    suspend fun getJokes(
        @Query("amount") numberOfJokes: Int = 10
    ): JokesResponse
}