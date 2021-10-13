package com.example.jokesapp.data.mapper

import com.example.jokesapp.data.network.dto.Jokes
import com.example.jokesapp.domain.Model.Joke

fun Jokes.toJokesDomainModel(): List<Joke> {
    return this.map {
        Joke(
            it.delivery, it.id, it.type, it.joke, it.setup
        )
    }
}