package com.example.myflix.core.domain.repository

import com.example.myflix.core.data.source.remote.dto.response.MovieItemResponse
import com.example.myflix.core.data.source.remote.dto.response.WebResponse

interface MovieRepository {

    suspend fun getMovie(genre: String) : WebResponse<List<MovieItemResponse>>
    suspend fun getMovieDetail(movieId: String) : WebResponse<MovieItemResponse>
    suspend fun storeWatchList(movieId: String): WebResponse<MovieItemResponse>
    suspend fun removeWatchList(movieId: String): WebResponse<MovieItemResponse>
    suspend fun getWatchList(): WebResponse<List<MovieItemResponse>>
}