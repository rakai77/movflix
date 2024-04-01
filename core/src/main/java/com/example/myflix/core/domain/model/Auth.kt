package com.example.myflix.core.domain.model

data class Auth(
    val user: User? = null,
    val token: String? = null
)