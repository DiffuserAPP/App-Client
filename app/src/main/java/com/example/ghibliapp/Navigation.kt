package com.example.ghibliapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ghibliapp.Destinations.IMG_INPUT_SCREEN
import com.example.ghibliapp.Destinations.TEXT_INPUT_SCREEN
import com.example.ghibliapp.Destinations.WELCOME_SCREEN

object Destinations {
    const val WELCOME_SCREEN = "welcome"
    const val TEXT_INPUT_SCREEN = "text input"
    const val IMG_INPUT_SCREEN = "image input"
    const val GENERATED_RESULT_SCREEN = "generated result" //TODO
}

@Composable
fun GhibliAppNavHost( )
{
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome")
    {
        composable(route= WELCOME_SCREEN) {
            WelcomeScreen(navController)
        }
        composable(TEXT_INPUT_SCREEN)
        { backStackEntry ->
            TextInputScreen(navController)
        }
        composable(IMG_INPUT_SCREEN)
        { backStackEntry ->
            ImgInputScreen(navController)
        }
    }

}

