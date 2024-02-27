package com.example.myflix.core.data.interactor

import com.example.myflix.core.data.source.remote.SafeApiCall
import com.example.myflix.core.data.source.remote.dto.response.WebResponse
import com.example.myflix.core.data.source.remote.dto.response.toDomain
import com.example.myflix.core.domain.model.MovieItem
import com.example.myflix.core.domain.model.Resource
import com.example.myflix.core.domain.repository.MovieRepository
import com.example.myflix.core.domain.usecase.MovieUseCase
import com.example.myflix.core.utils.execute
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

class MovieInteractor constructor(
    private val coroutineContext: CoroutineContext,
    private val movieRepository: MovieRepository
) : MovieUseCase, SafeApiCall {
    override suspend fun getMovie(genre: String): Flow<Resource<WebResponse<List<MovieItem>>>> {
        return execute(coroutineContext) {
            safeApiCall {
                movieRepository.getMovie(genre).run {
                    WebResponse(
                        data?.map { it.toDomain() },
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