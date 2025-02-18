package net.azarquiel.parquesnretrofitjpc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.azarquiel.parquesnretrofitjpc.screens.MasterScreen
import net.azarquiel.parquesnretrofitjpc.screens.ParquesScreen
import net.azarquiel.parquesnretrofitjpc.screens.DetailScreen
import net.azarquiel.parquesnretrofitjpc.viewmodel.MainViewModel


@Composable
fun AppNavigation(viewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = AppScreens.MasterScreen.route){
        composable(AppScreens.MasterScreen.route){
            MasterScreen(navController, viewModel)
        }
        composable(AppScreens.ParquesScreen.route){
            ParquesScreen(navController, viewModel)
        }
        composable(AppScreens.DetailScreen.route) {
            DetailScreen(navController, viewModel)
        }
    }
}
sealed class AppScreens(val route: String) {
    object MasterScreen: AppScreens(route = "MasterScreen")
    object ParquesScreen: AppScreens(route = "ParquesScreen")
    object DetailScreen: AppScreens(route = "DetailScreen")
}
