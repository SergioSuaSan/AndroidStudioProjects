package net.azarquiel.parquesnretrofitjpc.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import net.azarquiel.parquesnretrofitjpc.R
import net.azarquiel.parquesnretrofitjpc.model.Comunidad
import net.azarquiel.parquesnretrofitjpc.model.Parques
import net.azarquiel.parquesnretrofitjpc.viewmodel.MainViewModel


@Composable
fun ParquesScreen(navController: NavHostController, viewModel: MainViewModel) {
    Scaffold(
        topBar = { ParquesTopBar(navController) },
        content = { padding ->
            ParquesContent(padding, viewModel, navController)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParquesTopBar(navController: NavHostController) {
    val comunidad = navController.previousBackStackEntry?.savedStateHandle?.get<Comunidad>("comunidad")

    TopAppBar(
        title = { comunidad?.let { Text(text = it.nombre) } },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}
@Composable
fun ParquesContent(
    padding: PaddingValues,
    viewModel: MainViewModel,
    navController: NavHostController
) {
    val parques = viewModel.parques.observeAsState(listOf())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),)
    {
        // a pintaaarrrr sabiendo que estamos en column
        LazyColumn (
            modifier = Modifier.padding(16.dp)
        ){
            items(parques.value) {
                CardParque(it, navController, viewModel)
            }
        }
    }
}

@Composable
fun CardParque(parque: Parques, navController: NavHostController, viewModel: MainViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                navController.currentBackStackEntry?.savedStateHandle?.set("parque", parque)
                navController.navigate("DetailScreen")
                viewModel.getImagenesByParque(parque.id)

            },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.purple_500),
            contentColor = Color.White),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),


        )
        {

            AsyncImage(
                model = parque.imagen,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()

            )

            Text(
                text = parque.nombre,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}


