package com.example.myflix.home.impl.presentation

sealed class HomeRoute(val route: String) {

    object Home: HomeRoute("home")
}