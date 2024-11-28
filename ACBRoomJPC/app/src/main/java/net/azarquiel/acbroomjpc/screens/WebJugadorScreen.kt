package net.azarquiel.acbroomjpc.screens

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import net.azarquiel.acbroomjpc.model.Jugador
import net.azarquiel.acbroomjpc.viewmodel.MainViewModel


@Composable
fun WebJugadorScreen(navController: NavHostController, viewModel: MainViewModel) {
    val jugador = navController.previousBackStackEntry?.savedStateHandle?.get<Jugador>("jugador")

    Scaffold(
        topBar = { WebJugadorTopBar(jugador) },
        content = { padding ->
            WebJugadorContent(padding, viewModel, navController, jugador)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebJugadorTopBar(jugador: Jugador?) {
    TopAppBar(
        title = {
            jugador?.let {

            Text(text = jugador.nombre) }},
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}


@Composable
fun WebJugadorContent(
    padding: PaddingValues,
    viewModel: MainViewModel,
    navController: NavHostController,
    jugador: Jugador?
) {
    jugador?.let {
        Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),)
    {
        // a pintaaarrrr sabiendo que estamos en column

        AndroidView(factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                loadUrl(jugador.link)
            }
        }, update = {
            it.loadUrl(jugador.link)
        })

    }}
}
