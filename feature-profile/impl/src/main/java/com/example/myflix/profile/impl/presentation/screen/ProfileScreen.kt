package com.example.myflix.profile.impl.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProfileScreen() {
    Text(
        modifier = Modifier.fillMaxSize(),
        text = "Profile",
        color = MaterialTheme.colorScheme.onBackground
    )
}