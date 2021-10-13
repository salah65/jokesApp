package com.example.jokesapp.domain.Model

import java.io.Serializable

data class Joke(
    val delivery: String? = null,
    val id: Int? = null,
    val type: String? = null,
    val joke: String? = null,
    val setup: String? = null,
) : Serializable