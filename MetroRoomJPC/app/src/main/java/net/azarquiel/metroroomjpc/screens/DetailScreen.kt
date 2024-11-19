package net.azarquiel.metroroomjpc.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import net.azarquiel.metroroomjpc.MainViewModel
import net.azarquiel.metroroomjpc.model.Estacion
import net.azarquiel.metroroomjpc.model.LineaWithEstaciones


@Composable
fun DetailScreen(navController: NavHostController, viewModel: MainViewModel) {
    Scaffold(
        topBar = { DetailTopBar(navController, viewModel) },
        content = { padding ->
            DetailContent(padding, viewModel, navController)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(navController: NavHostController, viewModel: MainViewModel) {
    TopAppBar(
        title = {
            Row(
                horizontalArrangement = Arrangement.Start,

                ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "",
                    Modifier.clickable {

                        navController.popBackStack()
                        // navController.navigate("MainScreen")
                    })
                Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                Text(text = "Estaciones")
            }
        },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )


}


@Composable
fun DetailContent(
    padding: PaddingValues,
    viewModel: MainViewModel,
    navController: NavHostController
) {
    //Saca
    val lineaWithEstaciones =
        navController.previousBackStackEntry?.savedStateHandle?.get<LineaWithEstaciones>("lineaWithEstaciones")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
    )
    {
        // a pintaaarrrr sabiendo que estamos en column
        lineaWithEstaciones?.let {
            val context = LocalContext.current
            val id = context.resources.getIdentifier(
                "icono_linea_${lineaWithEstaciones.linea.id}",
                "drawable",
                context.packageName
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(
                        Color(lineaWithEstaciones.linea.color.toColorInt()),
                        shape = RoundedCornerShape(12.dp)
                    ),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically

            )
            {
                Image(
                    painter = painterResource(id),
                    contentDescription = lineaWithEstaciones.linea.nombre,
                    modifier = Modifier
                        .weight(3f)
                        .size(100.dp, 100.dp)
                        .padding(8.dp)
                )
                Text(
                    text = lineaWithEstaciones.linea.nombre,
                    modifier = Modifier
                        .weight(8f)
                        .fillMaxWidth(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            lineaWithEstaciones.estaciones?.let { lineaWithEstaciones.linea }
            LazyColumn {

                itemsIndexed(lineaWithEstaciones.estaciones) { i, estacion ->
                    val color = lineaWithEstaciones.linea.color
                    CardEstacion(lineaWithEstaciones, estacion, color)
                }
            }
        }
    }
    Spacer(modifier = Modifier.padding(16.dp))

}


@Composable
fun CardEstacion(lineaWithEstaciones: LineaWithEstaciones, estaciones: Estacion, color: String) {
    var mycolor = color
    mycolor = mycolor.replace("#FF", "#55")
    mycolor = mycolor.replace("#A0", "#55")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(mycolor.toColorInt()),
            contentColor = Color.Black
        ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {

            Text(
                text = estaciones.nombre,
                modifier = Modifier
                    .weight(8f)
                    .fillMaxWidth(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }

}



