package net.azarquiel.metroroomjpc.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import net.azarquiel.metroroomjpc.MainViewModel
import net.azarquiel.metroroomjpc.R
import net.azarquiel.metroroomjpc.model.Linea
import net.azarquiel.metroroomjpc.model.LineaWithEstaciones


@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel) {
    Scaffold(
        topBar = { CustomTopBar() },
        content = { padding ->
            CustomContent(padding, viewModel, navController)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar() {
    TopAppBar(
        title = { Text(text = "Metro de Madrid Vuela!") },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}


@Composable
fun CustomContent(
    padding: PaddingValues,
    viewModel: MainViewModel,
    navController: NavHostController
) {
    val lineas = viewModel.lineas.observeAsState(listOf())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),)
    {
        LazyColumn {
            itemsIndexed(lineas.value) { i, linea ->
                if (i==0) {
                    Image(
                        painter = painterResource(id = R.drawable.metro),
                        contentDescription = "Metro de Madrid",
                    )
                }
                CardEstacion(linea, navController)
            }
        }
    }
}
@Composable
fun CardEstacion(lineaWithEstaciones: LineaWithEstaciones, navController: NavHostController) {
    val context = LocalContext.current
    val id = context.resources.getIdentifier("icono_linea_${lineaWithEstaciones.linea.id}", "drawable", context.packageName)
    var mycolor = lineaWithEstaciones.linea.color
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                // name es la variable de un TextField, podría ser un Objeto
                navController.currentBackStackEntry?.savedStateHandle?.set("lineaWithEstaciones", lineaWithEstaciones)
                navController.navigate("DetailScreen")
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(lineaWithEstaciones.linea.color.toColorInt()),
            contentColor = Color.Black),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Image(
                painter = painterResource(id),
                contentDescription = lineaWithEstaciones.linea.nombre,
                modifier = Modifier.weight(3f).size(100.dp,100.dp).padding(8.dp)
            )
            Text(
                text = lineaWithEstaciones.linea.nombre,
                modifier = Modifier.weight(8f).fillMaxWidth(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}


