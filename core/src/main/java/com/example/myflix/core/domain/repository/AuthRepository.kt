package com.example.myflix.core.domain.repository

import com.example.myflix.core.data.source.remote.dto.request.LoginRequest
import com.example.myflix.core.data.source.remote.dto.request.RegisterRequest
import com.example.myflix.core.data.source.remote.dto.response.AuthResponse
import com.example.myflix.core.data.source.remote.dto.response.WebResponse

interface AuthRepository {
    suspend fun login(request: LoginRequest): WebResponse<AuthResponse>
    suspend fun register(request: RegisterRequest): WebResponse<AuthResponse>
}