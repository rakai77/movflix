package com.example.myflix.design_system.presentation.utils

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

object MathUtils {

    fun lerp(
        start: Float,
        stop: Float,
        fraction: Float
    ) = start + (stop - start) * fraction.coerceIn(0f, 1f)

    fun calculateRotateHeight(originalWidth: Float, originalHeight: Float, angelDegrees: Float) : Float {
        val angelRadians = angelDegrees * (PI / 180).toFloat()
        return (originalWidth * abs(sin(angelRadians))) + (originalHeight * abs(cos(angelRadians)))
    }
}