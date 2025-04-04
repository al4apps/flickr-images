package com.example.pix.ui.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pix.ui.screens.PictureScreen
import com.example.pix.ui.screens.PicturesScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, Screens.Pictures.route) {
        composable(Screens.Pictures.route) { PicturesScreen(navController) }
        composable(
            route = Screens.Picture.route + "/{${Screens.URL}}",
            arguments = listOf(
                navArgument(Screens.URL, builder = {
                    type = NavType.StringType
                }
                )
            )

        ) {
            val url = it.arguments?.getString(Screens.URL)?.let { url ->
                Uri.decode(url)
            } ?: ""
            PictureScreen(navController, url = url)
        }
    }
}

sealed class Screens(val route: String) {
    data object Pictures : Screens("pictures")
    data object Picture : Screens("picture")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                val encoded = Uri.encode(arg)
                append("/$encoded")
            }
        }
    }

    companion object {
        const val URL = "url"
    }
}