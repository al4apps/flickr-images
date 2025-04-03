package com.example.pix.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pix.ui.screens.PictureScreen
import com.example.pix.ui.screens.PicturesScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, Screens.Picture.route) {
        composable(Screens.Pictures.route) { PicturesScreen(navController) }
        composable(Screens.Picture.route) { PictureScreen() }
    }
}

sealed class Screens(val route: String) {
    data object Pictures: Screens("pictures")
    data object Picture: Screens("picture")
}