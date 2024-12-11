package net.azarquiel.playasandaluciajpa.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.azarquiel.playasandaluciajpa.MainActivity
import net.azarquiel.playasandaluciajpa.screens.DetailScreen
import net.azarquiel.playasandaluciajpa.screens.MasterScreen
import net.azarquiel.playasandaluciajpa.screens.PlayasScreen
import net.azarquiel.playasandaluciajpa.viewmodel.MainViewModel

@Composable
fun AppNavigation(mainActivity: MainActivity
) {
    val navController = rememberNavController()
    val viewModel = MainViewModel(mainActivity)
    NavHost(navController = navController,
        startDestination = AppScreens.MasterScreen.route){
        composable(AppScreens.MasterScreen.route){
            MasterScreen(navController, viewModel)
        }

        composable(AppScreens.DetailScreen.route) {
            DetailScreen(navController, viewModel)
        }

        composable(AppScreens.PlayasScreen.route) {
            PlayasScreen(navController, viewModel)
        }


    }
}
sealed class AppScreens(val route: String) {
    object MasterScreen: AppScreens(route = "MasterScreen")
    object DetailScreen: AppScreens(route = "DetailScreen")
    object PlayasScreen: AppScreens(route = "PlayasScreen")
}