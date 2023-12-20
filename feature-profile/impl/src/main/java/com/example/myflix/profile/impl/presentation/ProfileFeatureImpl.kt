package com.example.myflix.profile.impl.presentation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.example.myflix.design_system.presentation.utils.nonAnimationComposable
import com.example.myflix.profile.api.ProfileFeature
import com.example.myflix.profile.impl.presentation.screen.ProfileScreen

class ProfileFeatureImpl : ProfileFeature {
    override val profileRoute: String
        get() = ProfileRoute.Profile.route

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.nonAnimationComposable(
            route = ProfileRoute.Profile.route
        ) {
            ProfileScreen()
        }
    }
}