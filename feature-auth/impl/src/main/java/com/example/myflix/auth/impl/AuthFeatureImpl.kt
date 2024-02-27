package com.example.myflix.auth.impl

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.example.myflix.auth.api.AuthFeature
import com.example.myflix.auth.impl.presentation.screen.login.LoginScreen
import com.example.myflix.auth.impl.presentation.screen.login.LoginViewModel
import com.example.myflix.auth.impl.presentation.screen.register.RegisterScreen
import com.example.myflix.design_system.utils.composable
import com.example.myflix.home.impl.presentation.HomeRoute

class AuthFeatureImpl : AuthFeature {
    override val authRoute: String
        get() = AuthRoute.Login.route

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(
            route = AuthRoute.Login.route
        ) {
            val viewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(
                viewModel = viewModel,
                onToRegister = {
                    navController.navigate(AuthRoute.Register.route)
                },
                onSuccessLogin = {
                    navController.navigate(HomeRoute.Home.route)
                }
            )
        }

        navGraphBuilder.composable(
            route = AuthRoute.Register.route
        ) {
            RegisterScreen(
                onToLoginScreen = {
                    navController.popBackStack()
                }
            )
        }
    }
}