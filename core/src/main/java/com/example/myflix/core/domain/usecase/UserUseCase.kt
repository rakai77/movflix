package com.example.myflix.core.domain.usecase

import com.example.myflix.core.data.source.remote.dto.response.WebResponse
import com.example.myflix.core.domain.model.Resource
import com.example.myflix.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    suspend fun getProfile(): Flow<Resource<WebResponse<User>>>
}