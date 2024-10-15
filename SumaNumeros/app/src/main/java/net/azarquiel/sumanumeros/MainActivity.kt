package net.azarquiel.sumanumeros

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import net.azarquiel.sumanumeros.ui.theme.SumaNumerosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SumaNumerosTheme {
                MainScreen(MainViewModel(this))
            }
        }
    }
}



