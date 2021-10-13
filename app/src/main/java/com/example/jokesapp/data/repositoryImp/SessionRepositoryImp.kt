package com.example.jokesapp.data.repositoryImp

import com.example.jokesapp.data.core.SESSION_COUNT
import com.example.jokesapp.data.gateways.PreferencesGateway
import com.example.jokesapp.domain.repository.SessionRepo
import javax.inject.Inject

class SessionRepositoryImp @Inject constructor (private val preferences: PreferencesGateway) :
    SessionRepo {
    override fun cacheNumberOfSession(numberOfSession: Int) {
        preferences.save(SESSION_COUNT, numberOfSession)
    }

    override fun getNumberOfSession(): Int? {
        return preferences.load(SESSION_COUNT, 0)
    }
}