package com.example.myflix.home.impl.presentation.screen.home

import com.example.myflix.core.domain.model.MovieItem
import com.example.myflix.core.domain.model.User

data class HomeUiState(
    val isProfileLoading: Boolean = true,
    val isMoviesLoading: Boolean = true,
    val isProfileError: Boolean = false,
    val isMoviesError: Boolean = false,
    val profileErrorMessage: String? = null,
    val moviesErrorMessage: String? = null,
    val movies: List<MovieItem> = emptyList(),
    val profile: User? = null
)