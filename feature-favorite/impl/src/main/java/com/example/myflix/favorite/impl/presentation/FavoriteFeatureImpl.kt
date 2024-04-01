package com.example.myflix.favorite.impl.presentation

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.example.myflix.design_system.utils.nonAnimationComposable
import com.example.myflix.favorite.api.FavoriteFeature
import com.example.myflix.favorite.impl.presentation.screen.FavoriteScreen
import com.example.myflix.favorite.impl.presentation.screen.FavoriteViewModel
import com.example.myflix.home.api.HomeRoute

class FavoriteFeatureImpl : FavoriteFeature {
    override val favoriteRoute: String
        get() = FavoriteRoute.Favorite.route

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.nonAnimationComposable(
            route = FavoriteRoute.Favorite.route
        ) {
            val viewModel: FavoriteViewModel = hiltViewModel()
            FavoriteScreen(
                viewModel = viewModel,
                onMovieClick = { movieId ->
                    navController.navigate(HomeRoute.MovieDetail.route.replace("{movieId}", movieId))
                }
            )
        }
    }
}