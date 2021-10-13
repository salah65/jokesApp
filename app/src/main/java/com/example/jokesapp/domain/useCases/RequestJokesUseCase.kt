package com.example.jokesapp.domain.useCases

import com.example.jokesapp.domain.Model.Joke
import com.example.jokesapp.domain.repository.JokeRepo
import javax.inject.Inject


class RequestJokesUseCase @Inject constructor(private val repository: JokeRepo) {
    suspend operator fun invoke(): List<Joke> {
        return runCatching {
            repository.requestJokes()
        }.getOrElse {
            emptyList()
        }
    }
}