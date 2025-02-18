package net.azarquiel.chistesretrofitjpc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.azarquiel.chistesretrofitjpc.MainActivity
import net.azarquiel.chistesretrofitjpc.screens.CategoriasScreen
import net.azarquiel.chistesretrofitjpc.viewmodel.MainViewModel

@Composable
fun AppNavigation(viewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = AppScreens.CategoriasScreen.route){
        composable(AppScreens.CategoriasScreen.route){
            CategoriasScreen(navController, viewModel)
        }
    }
}
sealed class AppScreens(val route: String) {
    object CategoriasScreen: AppScreens(route = "CategoriasScreen")
}
