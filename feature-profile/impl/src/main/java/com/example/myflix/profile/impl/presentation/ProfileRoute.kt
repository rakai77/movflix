package com.example.myflix.profile.impl.presentation

sealed class ProfileRoute(val route: String) {

    object Profile : ProfileRoute("profile")
}