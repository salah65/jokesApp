package com.example.jokesapp.data.network.dto

data class JokesResponse(
    val amount: Int,
    val error: Boolean,
    val jokes: Jokes
)
typealias Jokes = List<JokeDto>