package com.example.myflix.core.presentation

sealed class BasicUiState<out T> {
    data class Success<T>(val data: T) : BasicUiState<T>()
    data class Error(val errorCode: Int, val message: String) : BasicUiState<Nothing>()
    object Loading: BasicUiState<Nothing>()
    object Idle: BasicUiState<Nothing>()
}