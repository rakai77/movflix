package com.example.myflix.home.impl.presentation.screen.home

import androidx.lifecycle.viewModelScope
import com.example.myflix.core.domain.model.Resource
import com.example.myflix.core.domain.usecase.MovieUseCase
import com.example.myflix.core.domain.usecase.UserUseCase
import com.example.myflix.core.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val userUseCase: UserUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    fun getMovie(genre: String) {
        viewModelScope.launch {
            movieUseCase.getMovie(genre).runFlow { result ->
                when(result) {
                    is Resource.Loading -> {
                        _uiState.update {
                            it.copy(
                                isMoviesLoading = true
                            )
                        }
                    }
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                isMoviesLoading = false,
                                isMoviesError = false,
                                moviesErrorMessage = null,
                                movies = result.data.data.orEmpty()
                            )
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isMoviesLoading = false,
                                isMoviesError = true,
                                moviesErrorMessage = result.message
                            )
                        }
                    }
                    else -> Unit
                }
            }
        }
    }

    fun getProfile() {
        viewModelScope.launch {
            userUseCase.getProfile().runFlow { result ->
                when(result) {
                    is Resource.Loading -> {
                        _uiState.update {
                            it.copy(
                                isProfileLoading = true
                            )
                        }
                    }
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                isProfileLoading = false,
                                isProfileError = false,
                                profileErrorMessage = null,
                                profile = result.data.data
                            )
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isProfileLoading = false,
                                isProfileError = true,
                                profileErrorMessage = result.message
                            )
                        }
                    }
                    else -> Unit
                }
            }
        }
    }
}