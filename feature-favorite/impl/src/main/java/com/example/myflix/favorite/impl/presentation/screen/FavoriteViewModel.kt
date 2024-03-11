package com.example.myflix.favorite.impl.presentation.screen

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
class FavoriteViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : BaseViewModel() {

    private var _uiState = MutableSharedFlow<BasicUiState<WebResponse<List<MovieItem>>>>()
    val uiState = _uiState.asSharedFlow()

    fun getWatchList() {
        viewModelScope.launch {
            movieUseCase.getWatchList().runFlow {
                when (val result = it) {
                    is Resource.Loading -> {
                        _uiState.emit(BasicUiState.Loading)
                    }
                    is Resource.Error -> {
                        _uiState.emit(BasicUiState.Error(result.errorCode, result.message))
                    }
                    is Resource.Success -> {
                        _uiState.emit(BasicUiState.Success(result.data))
                    }
                    else -> Unit
                }
            }
        }
    }
}