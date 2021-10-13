package com.example.jokesapp.data.repositoryImp

import com.example.jokesapp.data.gateways.ServerGateway
import com.example.jokesapp.data.mapper.toJokesDomainModel
import com.example.jokesapp.domain.Model.Joke
import com.example.jokesapp.domain.repository.JokeRepo
import javax.inject.Inject

class JokesRepositoryImp
@Inject constructor(
    private val server: ServerGateway
) : JokeRepo {
    override suspend fun requestJokes(): List<Joke> {
        return server.requestJokes().toJokesDomainModel()
    }
}