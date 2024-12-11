package net.azarquiel.playasandaluciajpa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import net.azarquiel.playasandaluciajpa.navegation.AppNavigation
import net.azarquiel.playasandaluciajpa.ui.theme.PlayasAndaluciaJPATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlayasAndaluciaJPATheme {
                AppNavigation(this)
            }
        }
    }
}
