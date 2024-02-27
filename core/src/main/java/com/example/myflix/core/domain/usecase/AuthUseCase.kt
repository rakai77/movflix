package com.example.myflix.core.domain.usecase

import com.example.myflix.core.data.source.remote.dto.request.LoginRequest
import com.example.myflix.core.data.source.remote.dto.request.RegisterRequest
import com.example.myflix.core.data.source.remote.dto.response.WebResponse
import com.example.myflix.core.domain.model.Auth
import com.example.myflix.core.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {
    suspend fun login(request: LoginRequest): Flow<Resource<WebResponse<Auth>>>
    suspend fun register(request: RegisterRequest): Flow<Resource<WebResponse<Auth>>>
}