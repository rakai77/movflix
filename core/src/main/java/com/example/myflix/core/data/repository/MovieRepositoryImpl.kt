package com.example.myflix.core.data.repository

import com.example.myflix.core.data.source.remote.dto.response.MovieItemResponse
import com.example.myflix.core.data.source.remote.dto.response.WebResponse
import com.example.myflix.core.data.source.remote.service.MovieService
import com.example.myflix.core.domain.repository.MovieRepository

class MovieRepositoryImpl constructor(
    private val movieService: MovieService
) : MovieRepository {
    override suspend fun getMovie(genre: String): WebResponse<List<MovieItemResponse>> {
        return movieService.getMovies(genre)
    }
}