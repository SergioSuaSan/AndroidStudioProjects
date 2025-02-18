package net.azarquiel.parquesnretrofitjpc.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import net.azarquiel.parquesnretrofitjpc.navigation.AppNavigation
import net.azarquiel.parquesnretrofitjpc.ui.theme.ParquesNRetroFitJPCTheme
import net.azarquiel.parquesnretrofitjpc.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // share viewModel aquí. Fuera de los composables
        val viewModel = MainViewModel(this)
        setContent {
            ParquesNRetroFitJPCTheme {
                AppNavigation(viewModel)

            }
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ParquesNRetroFitJPCTheme {
        Greeting("Android")
    }
}