package com.example.jokesapp.domain.useCases

import com.example.jokesapp.domain.repository.SessionRepo
import javax.inject.Inject

class SessionCountUseCase @Inject constructor(private val repository: SessionRepo) {
    fun cacheSessionCount() {
        repository.cacheNumberOfSession(repository.getNumberOfSession()?.inc() ?: 1)
    }

    fun getSessionCount(): Int {
        return repository.getNumberOfSession() ?: 0

    }
}
