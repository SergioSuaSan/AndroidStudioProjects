package net.azarquiel.metroroomjpc.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.azarquiel.metroroomjpc.MainActivity
import net.azarquiel.metroroomjpc.MainViewModel
import net.azarquiel.metroroomjpc.screens.DetailScreen
import net.azarquiel.metroroomjpc.screens.MainScreen


@Composable
fun AppNavigation(mainViewModel: MainActivity) {
    val navController = rememberNavController()
    val viewModel = MainViewModel( mainViewModel)
    NavHost(navController = navController,
        startDestination = AppScreens.MainScreen.route){
        composable(AppScreens.MainScreen.route){
            MainScreen(navController, viewModel)
        }
        composable(AppScreens.DetailScreen.route) {
            DetailScreen(navController, viewModel)
        }
    }
}
sealed class AppScreens(val route: String) {
    object MainScreen: AppScreens(route = "MainScreen")
    object DetailScreen: AppScreens(route = "DetailScreen")
}
