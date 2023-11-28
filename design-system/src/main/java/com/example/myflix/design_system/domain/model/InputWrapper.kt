package com.example.myflix.design_system.domain.model

data class InputWrapper<T>(
    val value: T,
    val error: String? = null
)