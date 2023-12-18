package com.example.myflix.presentation.navigation

import com.example.myflix.design_system.R
import com.example.myflix.design_system.domain.model.Tab

val TabDestination = listOf(
    Tab(
        route = "home",
        selectedIcon = R.drawable.ic_home_filled,
        unselectedIcon = R.drawable.ic_home_outlined
    ),
    Tab(
        route = "bookmark",
        selectedIcon = R.drawable.ic_bookmark_filled,
        unselectedIcon = R.drawable.ic_bookmark_outlined
    ),
    Tab(
        route = "profile",
        selectedIcon = R.drawable.ic_profile_filled,
        unselectedIcon = R.drawable.ic_profile_outlined
    )
)