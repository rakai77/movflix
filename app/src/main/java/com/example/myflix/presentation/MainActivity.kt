package com.example.myflix.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.myflix.auth.api.AuthFeature
import com.example.myflix.design_system.presentation.theme.MyFlixTheme
import com.example.myflix.home.api.HomeFeature
import com.example.myflix.presentation.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var authFeature: AuthFeature
    @Inject lateinit var homeFeature: HomeFeature

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFlixTheme {
                // A surface container using the 'background' color from the theme
                MyFlixApp(authFeature, homeFeature)
            }
        }
    }
}

@Composable
fun MyFlixApp(
    authFeature: AuthFeature,
    homeFeature: HomeFeature
) {

    val navController = rememberNavController()

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        AppNavigation(
            startDestination = authFeature.authRoute,
            navController = navController,
            authFeature = authFeature,
            homeFeature = homeFeature
        )
    }
}