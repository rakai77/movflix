package com.example.myflix.home.impl.presentation

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.myflix.design_system.utils.composable
import com.example.myflix.design_system.utils.nonAnimationComposable
import com.example.myflix.home.api.HomeFeature
import com.example.myflix.home.api.HomeRoute
import com.example.myflix.home.impl.presentation.screen.home.HomeScreen
import com.example.myflix.home.impl.presentation.screen.home.HomeViewModel
import com.example.myflix.home.impl.presentation.screen.movie_detail.MovieDetailScreen
import com.example.myflix.home.impl.presentation.screen.movie_detail.MovieDetailViewModel

class HomeFeatureImpl : HomeFeature {
    override val homeRoute: String
        get() = HomeRoute.Home.route

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.nonAnimationComposable(
            route = HomeRoute.Home.route
        ) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(
                viewModel = viewModel,
                onMovieClick = { movieId ->
                    navController.navigate(HomeRoute.MovieDetail.route.replace("{movieId}", movieId))
                }
            )
        }

        navGraphBuilder.composable(
            route = HomeRoute.MovieDetail.route,
            args = listOf(navArgument("movieId") { type = NavType.StringType })
        ) { navBackStackEntry ->
            val viewModel: MovieDetailViewModel = hiltViewModel()
            val movieId = navBackStackEntry.arguments?.getString("movieId").orEmpty()
            MovieDetailScreen(
                viewModel = viewModel,
                movieId = movieId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}