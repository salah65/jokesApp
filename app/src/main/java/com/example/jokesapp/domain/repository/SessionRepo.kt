package com.example.jokesapp.domain.repository


interface SessionRepo {
    fun getNumberOfSession(): Int? = null
    fun cacheNumberOfSession(numberOfSession: Int){}
}