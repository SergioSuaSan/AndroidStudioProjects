package net.azarquiel.a2pantallas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import net.azarquiel.a2pantallas.navegation.AppNavigation
import net.azarquiel.a2pantallas.ui.theme._2PantallasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _2PantallasTheme {

                AppNavigation()

            }
        }
    }
}

