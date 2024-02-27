package com.example.myflix.core.data.source.remote.dto.response

import com.example.myflix.core.domain.model.MovieItem

data class MovieItemResponse(
    val overview: String? = null,
    val posterUrl: String? = null,
    val releaseDate: String? = null,
    val id: Int? = null,
    val title: String? = null,
    val filmRate: String? = null
)


fun MovieItemResponse.toDomain() = MovieItem(
    this.overview,
    this.posterUrl,
    this.releaseDate,
    this.id,
    this.title,
    this.filmRate
)
