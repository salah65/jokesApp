package com.example.jokesapp.domain.useCases

import com.example.jokesapp.data.core.SESSION_COUNT
import com.example.jokesapp.domain.repository.SessionRepo
import org.junit.Assert.assertEquals
import org.junit.Test

class SessionCountUseCaseTest {
    @Test
    fun `cacheSessionCount() with 0 times session count then cache 1`() {
        val preferences = HashMap<String, Int>()
        val repository = object : SessionRepo {
            override fun cacheNumberOfSession(numberOfSession: Int) {
                preferences[SESSION_COUNT] = preferences[SESSION_COUNT]?.inc() ?: 1
            }
        }
        val sessionCountUseCase = SessionCountUseCase(repository)
        // act
        sessionCountUseCase.cacheSessionCount()
        // assert
        assertEquals(preferences[SESSION_COUNT],1)

    }
    @Test
    fun `cacheSessionCount() with N times session count then cache N+1 times`() {
        //arrange
        val preferences = HashMap<String, Int>()
        preferences[SESSION_COUNT]=15
        val repository = object : SessionRepo {
            override fun cacheNumberOfSession(numberOfSession: Int) {
                preferences[SESSION_COUNT] = preferences[SESSION_COUNT]?.inc() ?: 1
            }
        }
        val sessionCountUseCase = SessionCountUseCase(repository)
        // act
        sessionCountUseCase.cacheSessionCount()
        // assert
        assertEquals(preferences[SESSION_COUNT],16)

    }
    @Test
    fun `getSessionCount() with null times session count then return 0`() {
        //arrange
        val preferences = HashMap<String, Int>()
        val repository = object : SessionRepo {
            override fun getNumberOfSession(): Int? {
                return preferences[SESSION_COUNT]
            }
        }
        val sessionCountUseCase = SessionCountUseCase(repository)
        // act
        val actual=sessionCountUseCase.getSessionCount()
        // assert
        assertEquals(0,actual)

    }
    @Test
    fun `getSessionCount() with N times session count then return N`() {
        //arrange
        val preferences = HashMap<String, Int>()
        preferences[SESSION_COUNT] = 15
        val repository = object : SessionRepo {
            override fun getNumberOfSession(): Int? {
                return preferences[SESSION_COUNT]
            }
        }
        val sessionCountUseCase = SessionCountUseCase(repository)
        // act
        val actual=sessionCountUseCase.getSessionCount()
        // assert
        assertEquals(15,actual)

    }
}