package com.example.myflix.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.myflix.auth.api.AuthFeature
import com.example.myflix.design_system.presentation.utils.register
import com.example.myflix.home.api.HomeFeature

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    startDestination: String,
    navController: NavHostController,
    authFeature: AuthFeature,
    homeFeature: HomeFeature
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        register(authFeature, navController)
        register(homeFeature, navController)
    }
}