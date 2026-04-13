package com.rheivalseptian8600.asesment1mobpro.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rheivalseptian8600.asesment1mobpro.ui.screen.AboutScreen
import com.rheivalseptian8600.asesment1mobpro.ui.screen.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable(route = "home") {
            MainScreen(navController = navController)
        }
        composable(route = "about") {
            AboutScreen(navController = navController)
        }
    }
}