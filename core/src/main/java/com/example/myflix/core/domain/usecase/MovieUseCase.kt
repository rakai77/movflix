package com.example.myflix.core.domain.usecase

import com.example.myflix.core.data.source.remote.dto.response.WebResponse
import com.example.myflix.core.domain.model.MovieItem
import com.example.myflix.core.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    suspend fun getMovie(genre: String) : Flow<Resource<WebResponse<List<MovieItem>>>>
    suspend fun getMovieDetail(movieId: String) : Flow<Resource<WebResponse<MovieItem>>>
    suspend fun storeWatchList(movieId: String): Flow<Resource<WebResponse<MovieItem>>>
    suspend fun removeWatchList(movieId: String): Flow<Resource<WebResponse<MovieItem>>>
    suspend fun getWatchList(): Flow<Resource<WebResponse<List<MovieItem>>>>
}