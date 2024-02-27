package com.example.myflix.home.impl.presentation

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.example.myflix.design_system.utils.nonAnimationComposable
import com.example.myflix.home.api.HomeFeature
import com.example.myflix.home.impl.presentation.screen.home.HomeScreen
import com.example.myflix.home.impl.presentation.screen.home.HomeViewModel

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
            HomeScreen(viewModel = viewModel)
        }
    }
}