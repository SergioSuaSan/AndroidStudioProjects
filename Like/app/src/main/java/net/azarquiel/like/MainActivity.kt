package net.azarquiel.like

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.azarquiel.like.ui.theme.LikeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LikeTheme {
                MainScreen()
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
private fun MainScreen() {
    Scaffold(
        // Barra superior
        topBar = { CustomTopBar() },
        // Contenido principal
        content = { padding ->
            CustomContent(padding)
        }
    )
}

@Composable
fun CustomContent(padding: PaddingValues) {
    Column(
        // Modificadores de estilo de la columna
        modifier = Modifier
            // Ocupar todo el espacio disponible
            .fillMaxSize()
            .padding(padding),
        // Contenido de la aplicación
        content = {
            Contenido()

        }
    )
}

@Composable
fun Contenido() {
    var likes by rememberSaveable  { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row (verticalAlignment = Alignment.CenterVertically){
        Image(
            painterResource(R.drawable.heart), contentDescription = "Heart", )
        Text(text = likes.toString(), color = colorResource(R.color.red), fontSize = 100.sp, fontWeight = FontWeight.Bold)
        }
        Button(
            onClick = { likes++},
            colors = ButtonDefaults.buttonColors(colorResource(R.color.purple_700))
        ) {
            Text("Like")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar() {
    TopAppBar(
// Título de la barra superior
        title = { Text(text = "Title") },
        colors = topAppBarColors(
            containerColor = colorResource(R.color.fondo),
            titleContentColor = colorResource(R.color.fore),
        )
    )
}




