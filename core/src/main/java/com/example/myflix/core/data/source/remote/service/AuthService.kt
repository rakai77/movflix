package com.example.myflix.core.data.source.remote.service

import com.example.myflix.core.data.source.remote.dto.request.LoginRequest
import com.example.myflix.core.data.source.remote.dto.request.RegisterRequest
import com.example.myflix.core.data.source.remote.dto.response.AuthResponse
import com.example.myflix.core.data.source.remote.dto.response.WebResponse

interface AuthService {

    suspend fun login(request: LoginRequest): WebResponse<AuthResponse>
    suspend fun register(request: RegisterRequest): WebResponse<AuthResponse>
}