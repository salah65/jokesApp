package com.example.jokesapp.domain.useCases

import com.example.jokesapp.domain.Model.Joke
import com.example.jokesapp.domain.repository.JokeRepo
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class RequestJokesUseCaseTest {
    @Test
    fun `requestJokesUseCase() with NetworkConnection then return listOFJokes`() {
        //arrange
        runBlocking {
            val shouldShowThrowError = false
            val repository = object : JokeRepo {
                override suspend fun requestJokes(): List<Joke> {
                    return if (!shouldShowThrowError)
                        listOf(
                            Joke(),
                            Joke(),
                            Joke()
                        )
                    else emptyList()
                }
            }
            val requestJokesUseCase = RequestJokesUseCase(repository)
            // act
            val actual = requestJokesUseCase()
            val expected = listOf(
                Joke(),
                Joke(),
                Joke()
            )
            // assert
            Assert.assertEquals(expected, actual)
        }
    }
    @Test
    fun `requestJokesUseCase() with NetworkError then return emptyList`() {
        //arrange
        runBlocking {
            val shouldShowThrowError = true
            val repository = object : JokeRepo {
                override suspend fun requestJokes(): List<Joke> {
                    return if (!shouldShowThrowError)
                        listOf(
                            Joke(),
                            Joke(),
                            Joke()
                        )
                    else emptyList()
                }
            }
            val requestJokesUseCase = RequestJokesUseCase(repository)
            // act
            val actual = requestJokesUseCase()
            val expected = emptyList<Joke>()
            // assert
            Assert.assertEquals(expected, actual)
        }
    }
}