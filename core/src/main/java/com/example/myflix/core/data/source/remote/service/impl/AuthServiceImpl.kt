package com.example.myflix.core.data.source.remote.service.impl

import com.example.myflix.core.data.source.remote.HttpRoutes
import com.example.myflix.core.data.source.remote.dto.request.LoginRequest
import com.example.myflix.core.data.source.remote.dto.request.RegisterRequest
import com.example.myflix.core.data.source.remote.dto.response.AuthResponse
import com.example.myflix.core.data.source.remote.dto.response.WebResponse
import com.example.myflix.core.data.source.remote.service.AuthService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url

class AuthServiceImpl constructor(
    private val httpClient: HttpClient
) : AuthService {
    override suspend fun login(request: LoginRequest): WebResponse<AuthResponse> {
        return httpClient.post {
            url(HttpRoutes.LOGIN_URL)
            setBody(request)
        }.body()
    }

    override suspend fun register(request: RegisterRequest): WebResponse<AuthResponse> {
        return httpClient.post {
            url(HttpRoutes.REGISTER_URL)
            setBody(request)
        }.body()
    }
}