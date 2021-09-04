package com.aslan.learncompose.ui.navigation

import androidx.compose.animation.defaultDecayAnimationSpec
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String){
    object MainScreen : Screen("main_screen")
    object DetailScreen : Screen("detail_screen")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route ){
        composable(Screen.MainScreen.route){
            MainScreen(navController = navController)
        }

        val NAME_ARGUMENT = "name"
        composable(
            route = Screen.DetailScreen.route + "/{$NAME_ARGUMENT}",
            arguments = listOf(
                navArgument(NAME_ARGUMENT){
                    type = NavType.StringType
                    nullable = true
                }
            )
        ){
            DetailScreen(it.arguments?.getString(NAME_ARGUMENT))
        }
    }
}