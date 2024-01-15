package com.example.myflix.core.data.repository

import com.example.myflix.core.data.source.remote.dto.request.LoginRequest
import com.example.myflix.core.data.source.remote.dto.request.RegisterRequest
import com.example.myflix.core.data.source.remote.dto.response.AuthResponse
import com.example.myflix.core.data.source.remote.dto.response.WebResponse
import com.example.myflix.core.data.source.remote.service.AuthService
import com.example.myflix.core.domain.repository.AuthRepository

class AuthRepositoryImpl constructor(
    private val authService: AuthService
) : AuthRepository {
    override suspend fun login(request: LoginRequest): WebResponse<AuthResponse> {
        return authService.login(request)
    }

    override suspend fun register(request: RegisterRequest): WebResponse<AuthResponse> {
        return authService.register(request)
    }
}