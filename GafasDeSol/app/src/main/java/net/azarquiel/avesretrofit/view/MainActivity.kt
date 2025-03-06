package net.azarquiel.avesretrofit.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import net.azarquiel.avesretrofit.navegation.AppNavigation
import net.azarquiel.avesretrofit.ui.theme.AvesRetrofitTheme
import net.azarquiel.avesretrofit.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // share viewModel aquí. Fuera de los composables
        val viewModel = MainViewModel(this)
        setContent {
            AvesRetrofitTheme {
                AppNavigation(viewModel)
            }
        }
    }
}
