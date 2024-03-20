package com.example.myflix.home.impl.data

import com.example.myflix.home.impl.R
import kotlin.random.Random

val person = listOf(
    R.drawable.person_one,
    R.drawable.person_two,
    R.drawable.person_three,
    R.drawable.person_four,
    R.drawable.person_five
)

fun randomPersonImage() = person[Random.nextInt(0, 4)]