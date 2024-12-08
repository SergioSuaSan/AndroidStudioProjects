package net.azarquiel.pueblosbonitos.screens

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import net.azarquiel.pueblosbonitos.model.Pueblowp
import net.azarquiel.pueblosbonitos.viewmodel.MainViewModel

@Composable
fun LinkScreen(navController: NavHostController, viewModel: MainViewModel) {
    val link = navController.previousBackStackEntry?.savedStateHandle?.get<String>("link")
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true // Enable JavaScript if needed
                webViewClient = WebViewClient() // Handle navigation within the WebView
                link?.let { loadUrl(it) } // Load the URL if it's not null
            }
        },
        update = { webView ->
            // Update the WebView if needed (e.g., when the link changes)
            link?.let { webView.loadUrl(it) }
        }
    )

}







/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LinkTopBar(navController: NavHostController, viewModel: MainViewModel) {
    TopAppBar(
        title = {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
            ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription ="",
                    Modifier.clickable {

                        navController.popBackStack()
                    })
                Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                Text(text = "Pueblos Bonitos")
            }

        }
        ,
        colors = topAppBarColors(
            containerColor = Color.Blue,
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}
@Composable
fun LinkContent(
    padding: PaddingValues,
    navController: NavHostController,
    viewModel: MainViewModel,
    name: String?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(Color.Cyan),

        horizontalAlignment = Alignment.CenterHorizontally
    )
    {


    }

}
 */
