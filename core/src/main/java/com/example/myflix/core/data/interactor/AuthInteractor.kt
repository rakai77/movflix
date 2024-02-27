package com.example.myflix.core.data.interactor

import com.example.myflix.core.data.source.remote.SafeApiCall
import com.example.myflix.core.data.source.remote.dto.request.LoginRequest
import com.example.myflix.core.data.source.remote.dto.request.RegisterRequest
import com.example.myflix.core.data.source.remote.dto.response.WebResponse
import com.example.myflix.core.data.source.remote.dto.response.toDomain
import com.example.myflix.core.domain.model.Auth
import com.example.myflix.core.domain.model.Resource
import com.example.myflix.core.domain.repository.AuthRepository
import com.example.myflix.core.domain.usecase.AuthUseCase
import com.example.myflix.core.utils.execute
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

class AuthInteractor constructor(
    private val coroutineContext: CoroutineContext,
    private val authRepository: AuthRepository
) : AuthUseCase, SafeApiCall {
    override suspend fun login(request: LoginRequest): Flow<Resource<WebResponse<Auth>>> {
        return execute(coroutineContext) {
            safeApiCall {
                authRepository.login(request).run {
                    WebResponse(
                        data?.toDomain(),
                        success,
                        message,
                        statusCode,
                        error
                    )
                }
            }
        }
    }

    override suspend fun register(request: RegisterRequest): Flow<Resource<WebResponse<Auth>>> {
        return execute(coroutineContext) {
            safeApiCall {
                authRepository.register(request).run {
                    WebResponse(
                        data?.toDomain(),
                        success,
                        message,
                        statusCode,
                        error
                    )
                }
            }
        }
    }
}