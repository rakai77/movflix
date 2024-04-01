package com.example.myflix.core.data.repository

import com.example.myflix.core.data.source.remote.dto.response.UserResponse
import com.example.myflix.core.data.source.remote.dto.response.WebResponse
import com.example.myflix.core.data.source.remote.service.UserService
import com.example.myflix.core.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userService: UserService
) : UserRepository {
    override suspend fun getProfile(): WebResponse<UserResponse> {
        return userService.getProfile()
    }
}