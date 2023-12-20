package com.example.myflix.favorite.api

import com.example.myflix.design_system.presentation.navigation.FeatureEntry

interface FavoriteFeature : FeatureEntry {
    val favoriteRoute: String
}