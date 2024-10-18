package net.azarquiel.signstraficojpc


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import net.azarquiel.signstraficojpc.ui.theme.SignsTraficoJPCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SignsTraficoJPCTheme {
                MainScreen(MainViewModel(this))
            }
        }
    }
}



