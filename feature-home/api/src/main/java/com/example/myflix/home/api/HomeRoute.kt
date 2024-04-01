package com.example.myflix.home.api

sealed class HomeRoute(val route: String) {
    object Home: HomeRoute("home")
    object MovieDetail : HomeRoute("movie/{movieId}")
}