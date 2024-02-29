package com.example.myflix.presentation

import androidx.lifecycle.viewModelScope
import com.example.myflix.auth.impl.AuthRoute
import com.example.myflix.core.data.source.local.AppDataStore
import com.example.myflix.core.presentation.BaseViewModel
import com.example.myflix.home.impl.presentation.HomeRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStore: AppDataStore
) : BaseViewModel() {

    private var _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private var _startDestination = MutableStateFlow("")
    val startDestination = _startDestination.asStateFlow()

    init {
        getToken()
    }

    private fun getToken() {
        viewModelScope.launch {
            _startDestination.value = if (dataStore.token.first().isNotEmpty()) {
                HomeRoute.Home.route
            } else AuthRoute.Login.route
            _isLoading.value = false
        }
    }

}