package com.example.myflix.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.myflix.auth.api.AuthFeature
import com.example.myflix.design_system.presentation.theme.MyFlixTheme
import com.example.myflix.favorite.api.FavoriteFeature
import com.example.myflix.home.api.HomeFeature
import com.example.myflix.presentation.navigation.AppBottomBar
import com.example.myflix.presentation.navigation.AppNavigation
import com.example.myflix.profile.api.ProfileFeature
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var authFeature: AuthFeature
    @Inject lateinit var homeFeature: HomeFeature
    @Inject lateinit var favoriteFeature: FavoriteFeature
    @Inject lateinit var profileFeature: ProfileFeature

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                mainViewModel.isLoading.value
            }
        }
        setContent {
            val startDestination by mainViewModel.startDestination.collectAsState()
            if (startDestination.isNotEmpty()) {
                MyFlixTheme {
                    // A surface container using the 'background' color from the theme
                    MyFlixApp(startDestination, authFeature, homeFeature, favoriteFeature, profileFeature)
                }
            }
        }
    }
}

@Composable
fun MyFlixApp(
    startDestination: String,
    authFeature: AuthFeature,
    homeFeature: HomeFeature,
    favoriteFeature: FavoriteFeature,
    profileFeature: ProfileFeature
) {

    val navController = rememberNavController()

    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.BottomCenter,
    ) {
        AppNavigation(
            startDestination = startDestination,
            navController = navController,
            authFeature = authFeature,
            homeFeature = homeFeature,
            favoriteFeature = favoriteFeature,
            profileFeature = profileFeature
        )

        AppBottomBar(
            modifier = Modifier
                .clip(RoundedCornerShape(percent = 50))
                .background(Color.White),
            navController = navController,
            homeFeature = homeFeature,
            favoriteFeature = favoriteFeature,
            profileFeature = profileFeature
        )
    }
}