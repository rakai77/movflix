package com.example.myflix.core.presentation

import androidx.lifecycle.ViewModel
import com.example.myflix.core.domain.model.Resource
import kotlinx.coroutines.flow.Flow

abstract class BaseViewModel : ViewModel() {

    suspend fun <T> Flow<Resource<T>>.runFlow(stateVariable: suspend (Resource<T>) -> Unit) {
        stateVariable.invoke(Resource.Loading)
        collect { result ->
            stateVariable.invoke(result)
        }
    }
}