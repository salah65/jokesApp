package com.example.jokesapp.data.gateways

import com.example.jokesapp.data.network.Endpoints
import com.example.jokesapp.data.network.dto.Jokes
import javax.inject.Inject


class ServerGatewayImplementer @Inject constructor(
    private val api: Endpoints
) :
    ServerGateway {
    override suspend fun requestJokes(): Jokes {
        return api.getJokes().jokes
    }


}

interface ServerGateway {

    suspend fun requestJokes(): Jokes
}