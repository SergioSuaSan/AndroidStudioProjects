package net.azarquiel.examen.navigation

//importar el navigation
import android.telecom.Call.Details
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.azarquiel.examen.screens.DetailsScreen

//arreglar dependencias con nuestro paquete y el nombre de nuestras pantallas
import net.azarquiel.examen.screens.MasterScreen
import net.azarquiel.examen.screens.DetailsScreenTopBar
import net.azarquiel.examenplantilla.MainActivity
import net.azarquiel.examenplantilla.viewmodel.MainViewModel


@Composable
fun AppNavigation(mainActivity: MainActivity)
{
    val navController = rememberNavController()
    val viewModel = MainViewModel(mainActivity)
    NavHost(navController = navController,
        startDestination = AppScreens.MasterScreen.route){
        composable(AppScreens.MasterScreen.route){
            MasterScreen(navController, viewModel)//añadele 1er parámetro
        }
        composable(AppScreens.DetailsScreen.route) {
            DetailsScreen(navController, viewModel)//añadele 1er parámetro
        }
    }
}
//recuerda poner el nombre bien de las pantallas
sealed class AppScreens(val route: String) {
    object MasterScreen: AppScreens(route = "MasterScreen")
    object DetailsScreen: AppScreens(route = "DetailsScreen")

}
