package com.example.myflix.auth.impl.presentation.screen.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable fun LoginScreen(
    onToRegister: () -> Unit
) {
    Column {
        Text(text = "Login Screen")
        Button(onClick = { onToRegister() }) {
            Text(text = "Go to register screen")
        }
    }
}