package com.example.myflix.favorite.impl.presentation

sealed class FavoriteRoute(val route: String) {

    object Favorite : FavoriteRoute("favorite")
}