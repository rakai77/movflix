package com.example.myflix.core.domain.repository

import com.example.myflix.core.data.source.remote.dto.response.UserResponse
import com.example.myflix.core.data.source.remote.dto.response.WebResponse

interface UserRepository {
    suspend fun getProfile() : WebResponse<UserResponse>
}