package com.example.jokesapp.domain.repository

import com.example.jokesapp.domain.Model.Joke


interface JokeRepo {
    suspend fun requestJokes(): List<Joke> {
        return emptyList()
    }
}