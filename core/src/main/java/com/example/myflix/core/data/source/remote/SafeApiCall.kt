package com.example.myflix.core.data.source.remote

import com.example.myflix.core.data.source.remote.dto.response.WebResponse
import com.example.myflix.core.domain.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

interface SafeApiCall {

    suspend fun<T> safeApiCall(
        apiCall: suspend () -> WebResponse<T>
    ) : Resource<WebResponse<T>> {
        return withContext(Dispatchers.IO) {
            try {
                val result = apiCall.invoke()
                if (result.success == true) {
                    Resource.Success(result)
                } else {
                    Resource.Error(result.statusCode ?: -1, result.error ?: "Ups, something error!")
                }
            } catch (throwable: Throwable) {
                when(throwable) {
                    is IOException -> {
                        Resource.Error(-1, throwable.message ?: "Ups, something error with your internet connection")
                    }
                    else -> {
                        Resource.Error(-1, throwable.message ?: "Ups, something error!")
                    }
                }
            }
        }
    }
}