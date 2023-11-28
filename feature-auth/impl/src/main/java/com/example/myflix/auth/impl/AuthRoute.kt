package com.example.myflix.auth.impl

sealed class AuthRoute(val route: String) {

    object Login: AuthRoute("login")
    object Register: AuthRoute("register")
}