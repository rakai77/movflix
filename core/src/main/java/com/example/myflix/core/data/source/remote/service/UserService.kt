package com.example.myflix.core.data.source.remote.service

import com.example.myflix.core.data.source.remote.dto.response.UserResponse
import com.example.myflix.core.data.source.remote.dto.response.WebResponse

interface UserService {
    suspend fun getProfile(): WebResponse<UserResponse>
}