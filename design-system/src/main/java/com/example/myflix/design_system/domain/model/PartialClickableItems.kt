package com.example.myflix.design_system.domain.model

import androidx.compose.ui.graphics.Color

enum class PartialClickableTextType {
    NORMAL, CLICKABLE
}

data class PartialClickableItems(
    val text: String,
    val color: Color,
    val type: PartialClickableTextType = PartialClickableTextType.NORMAL,
    val onClick: (() -> Unit) = {}
)