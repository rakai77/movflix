package com.example.myflix.home.impl.utils

fun String.getYearByDate() : String {
    return this.split("-").first()
}