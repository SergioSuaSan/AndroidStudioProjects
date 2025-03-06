package net.azarquiel.avesretrofit.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.azarquiel.avesretrofit.screens.DetailScreen
import net.azarquiel.avesretrofit.screens.RecursoDetailScreen
import net.azarquiel.avesretrofit.screens.RecursosScreen
import net.azarquiel.avesretrofit.screens.ZonaScreen
import net.azarquiel.avesretrofit.viewmodel.MainViewModel


@Composable
fun AppNavigation(viewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = AppScreens.ZonaScreen.route){
        composable(AppScreens.ZonaScreen.route){
            ZonaScreen(navController, viewModel)
        }
        composable(AppScreens.DetailScreen.route){
            DetailScreen(navController, viewModel)
        }
        composable(AppScreens.RecursosScreen.route){
            RecursosScreen(navController, viewModel)
        }
        composable(AppScreens.RecursoDetailScreen.route){
            RecursoDetailScreen(navController, viewModel)
        }
    }

}
sealed class AppScreens(val route: String) {
    object ZonaScreen: AppScreens(route = "ZonaScreen")
    object DetailScreen: AppScreens(route = "DetailScreen")
    object RecursosScreen: AppScreens(route = "RecursosScreen")
    object RecursoDetailScreen: AppScreens(route = "RecursoDetailScreen")
}
