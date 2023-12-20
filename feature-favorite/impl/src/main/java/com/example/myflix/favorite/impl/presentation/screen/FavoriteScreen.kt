package com.example.myflix.favorite.impl.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FavoriteScreen() {
    Text(
        modifier = Modifier.fillMaxSize(),
        text = "Favorite",
        color = MaterialTheme.colorScheme.onBackground
    )
}