package com.example.myflix.auth.impl.presentation.screen.register

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun RegisterScreen(
    onToLoginScreen: () -> Unit
) {
    Column {
        Text(text = "Register Screen")
        Button(onClick = { onToLoginScreen() }) {
            Text(text = "Go to login screen")
        }
    }
}