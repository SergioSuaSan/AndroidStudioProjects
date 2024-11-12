package net.azarquiel.a2pantallas.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.azarquiel.a2pantallas.MainViewModel
import net.azarquiel.a2pantallas.screens.DetailScreen
import net.azarquiel.a2pantallas.screens.MasterScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel = MainViewModel()
    NavHost(navController = navController,
        startDestination = AppScreens.MasterScreen.route){
        composable(AppScreens.MasterScreen.route){
            MasterScreen(navController, viewModel)
        }
        composable(AppScreens.DetailScreen.route) {
            DetailScreen(navController, viewModel)
        }
    }
}
