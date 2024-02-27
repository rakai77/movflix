package com.example.myflix.core.domain.usecase

import com.example.myflix.core.data.source.remote.dto.response.WebResponse
import com.example.myflix.core.domain.model.MovieItem
import com.example.myflix.core.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    suspend fun getMovie(genre: String) : Flow<Resource<WebResponse<List<MovieItem>>>>
}