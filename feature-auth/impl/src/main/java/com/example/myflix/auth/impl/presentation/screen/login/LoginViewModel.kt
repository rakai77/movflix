package com.example.myflix.auth.impl.presentation.screen.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.myflix.auth.impl.presentation.BasicUiState
import com.example.myflix.core.data.source.local.AppDataStore
import com.example.myflix.core.data.source.remote.dto.request.LoginRequest
import com.example.myflix.core.data.source.remote.dto.response.AuthResponse
import com.example.myflix.core.data.source.remote.dto.response.WebResponse
import com.example.myflix.core.domain.model.Resource
import com.example.myflix.core.domain.usecase.AuthUseCase
import com.example.myflix.core.presentation.BaseViewModel
import com.example.myflix.core.utils.InputValidator
import com.example.myflix.design_system.domain.model.InputWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val dataStore: AppDataStore
) : BaseViewModel() {

    var emailInput = mutableStateOf(InputWrapper(""))
        private set

    var passwordInput = mutableStateOf(InputWrapper(""))
        private set


    private val _uiState = MutableSharedFlow<BasicUiState<WebResponse<AuthResponse>>>()
    val uiState = _uiState.asSharedFlow()


    fun storeToken(value: String) {
        viewModelScope.launch {
            dataStore.storeData(AppDataStore.TOKEN, value)
        }
    }

    fun onEmailInput(email: String) {
        val error = InputValidator.getEmailErrorOrNull(email)
        emailInput.value = InputWrapper(email, error)
    }

    fun onPasswordInput(password: String) {
        val error = InputValidator.getPasswordErrorOrNull(password)
        passwordInput.value = InputWrapper(password, error)
    }

    fun login() {
        viewModelScope.launch {
            authUseCase.login(
                LoginRequest(
                    email = emailInput.value.value,
                    password = passwordInput.value.value
                )
            ).runFlow {
                when(it) {
                    is Resource.Loading -> {
                        _uiState.emit(BasicUiState.Loading)
                    }
                    is Resource.Success -> {
                        _uiState.emit(BasicUiState.Success(it.data))
                    }
                    is Resource.Error -> {
                        _uiState.emit(BasicUiState.Error(it.errorCode, it.message))
                    }
                    else -> Unit
                }
            }
        }
    }
}