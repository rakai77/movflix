package com.example.myflix.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.myflix.auth.api.AuthFeature
import com.example.myflix.design_system.presentation.utils.register

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    startDestination: String,
    navController: NavHostController,
    authFeature: AuthFeature
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        register(authFeature, navController)
    }
}