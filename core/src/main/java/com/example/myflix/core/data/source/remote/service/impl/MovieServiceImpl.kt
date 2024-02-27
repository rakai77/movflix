package com.example.myflix.core.data.source.remote.service.impl

import com.example.myflix.core.data.source.remote.HttpRoutes
import com.example.myflix.core.data.source.remote.dto.response.MovieItemResponse
import com.example.myflix.core.data.source.remote.dto.response.WebResponse
import com.example.myflix.core.data.source.remote.service.MovieService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

class MovieServiceImpl(
    private val httpClient: HttpClient
) : MovieService {
    override suspend fun getMovies(genre: String): WebResponse<List<MovieItemResponse>> {
        return httpClient.get {
            url(HttpRoutes.MOVIES)
            parameter("genreId",genre)
        }.body()
    }
}