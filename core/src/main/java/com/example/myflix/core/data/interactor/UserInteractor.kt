package com.example.myflix.core.data.interactor

import com.example.myflix.core.data.source.remote.SafeApiCall
import com.example.myflix.core.data.source.remote.dto.response.WebResponse
import com.example.myflix.core.data.source.remote.dto.response.toDomain
import com.example.myflix.core.domain.model.Resource
import com.example.myflix.core.domain.model.User
import com.example.myflix.core.domain.repository.UserRepository
import com.example.myflix.core.domain.usecase.UserUseCase
import com.example.myflix.core.utils.execute
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

class UserInteractor(
    private val coroutineContext: CoroutineContext,
    private val userRepository: UserRepository
) : UserUseCase, SafeApiCall {
    override suspend fun getProfile(): Flow<Resource<WebResponse<User>>> {
        return execute(coroutineContext) {
            safeApiCall {
                userRepository.getProfile().run {
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