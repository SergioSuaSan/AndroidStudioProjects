package net.azarquiel.chistesretrofitjpc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import net.azarquiel.chistesretrofitjpc.navigation.AppNavigation
import net.azarquiel.chistesretrofitjpc.ui.theme.ChistesRetrofitJPCTheme
import net.azarquiel.chistesretrofitjpc.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // share viewModel aquí. Fuera de los composables
        val viewModel = MainViewModel(this)
        setContent {
            ChistesRetrofitJPCTheme {
                AppNavigation(viewModel)
            }
        }
    }
}