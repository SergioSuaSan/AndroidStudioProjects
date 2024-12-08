package net.azarquiel.pueblosbonitos.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.azarquiel.pueblosbonitos.MainActivity
import net.azarquiel.pueblosbonitos.screens.DetailScreen
import net.azarquiel.pueblosbonitos.screens.LinkScreen
import net.azarquiel.pueblosbonitos.screens.MasterScreen
import net.azarquiel.pueblosbonitos.screens.PueblosScreen
import net.azarquiel.pueblosbonitos.viewmodel.MainViewModel

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
        composable(AppScreens.PueblosScreen.route) {
            PueblosScreen(navController, viewModel)
        }
        composable(AppScreens.LinkScreen.route) {
            LinkScreen(navController, viewModel)
        }
    }
}
sealed class AppScreens(val route: String) {
    object MasterScreen: AppScreens(route = "MasterScreen")
    object PueblosScreen: AppScreens(route = "PueblosScreen")
    object DetailScreen: AppScreens(route = "DetailScreen")
    object LinkScreen: AppScreens(route = "LinkScreen")
}