package com.example.myflix.core.data.source.remote.service.impl

import com.example.myflix.core.data.source.remote.HttpRoutes
import com.example.myflix.core.data.source.remote.dto.response.UserResponse
import com.example.myflix.core.data.source.remote.dto.response.WebResponse
import com.example.myflix.core.data.source.remote.service.UserService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url

class UserServiceImpl(private val httpClient: HttpClient) : UserService {
    override suspend fun getProfile(): WebResponse<UserResponse> {
        return httpClient.get {
            url(HttpRoutes.USER)
        }.body()
    }
}