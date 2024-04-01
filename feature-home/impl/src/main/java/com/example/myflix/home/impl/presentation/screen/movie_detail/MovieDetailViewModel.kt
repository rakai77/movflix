package com.example.myflix.home.impl.presentation.screen.movie_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.myflix.core.data.source.remote.dto.response.WebResponse
import com.example.myflix.core.domain.model.MovieItem
import com.example.myflix.core.domain.model.Resource
import com.example.myflix.core.domain.usecase.MovieUseCase
import com.example.myflix.core.presentation.BaseViewModel
import com.example.myflix.core.presentation.BasicUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : BaseViewModel() {

    private var _getMovieState = MutableSharedFlow<BasicUiState<WebResponse<MovieItem>>>()
    val getMovieUiState = _getMovieState.asSharedFlow()

    var isUserWatchList by mutableStateOf(false)

    fun getMovie(movieId: String) {
        viewModelScope.launch {
            movieUseCase.getMovieDetail(movieId).runFlow {
                when (it) {
                    is Resource.Loading -> {
                        _getMovieState.emit(BasicUiState.Loading)
                    }
                    is Resource.Success -> {
                        _getMovieState.emit(BasicUiState.Success(it.data))
                    }
                    is Resource.Error -> {
                        _getMovieState.emit(BasicUiState.Error(it.errorCode, it.message))
                    }
                    else -> Unit
                }
            }
        }
    }

    fun storeWatchList(movieId: String) {
        viewModelScope.launch {
            movieUseCase.storeWatchList(movieId).runFlow {
                when (it) {
                    is Resource.Success -> {
                        isUserWatchList = true
                    }
                    else -> Unit
                }
            }
        }
    }

    fun removeWatchList(movieId: String) {
        viewModelScope.launch {
            movieUseCase.removeWatchList(movieId).runFlow {
                when(it) {
                    is Resource.Success -> {
                        isUserWatchList = false
                    }
                    else -> Unit
                }
            }
        }
    }
}