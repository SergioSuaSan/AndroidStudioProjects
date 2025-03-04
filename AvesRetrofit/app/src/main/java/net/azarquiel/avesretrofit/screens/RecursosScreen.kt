package net.azarquiel.avesretrofit.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import net.azarquiel.avesretrofit.R
import net.azarquiel.avesretrofit.model.Recurso
import net.azarquiel.avesretrofit.model.Zona
import net.azarquiel.avesretrofit.navegation.AppScreens
import net.azarquiel.avesretrofit.viewmodel.MainViewModel
import androidx.compose.foundation.Image as Image


@Composable
fun RecursosScreen(navController: NavHostController, viewModel: MainViewModel) {
    val zona = navController.previousBackStackEntry?.savedStateHandle?.get<Zona>("zona")
    Scaffold(
        topBar = { RecursosTopBar(zona) },
        content = { padding ->
            RecursosContent(padding, navController, viewModel)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecursosTopBar(zona: Zona?) {
    TopAppBar(
        title = {

                Text(
                    text = "Recursos ${zona?.nombre}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,

                )

            },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}


@Composable
fun RecursosContent(
    padding: PaddingValues,
    navController: NavHostController,
    viewModel: MainViewModel
) {
    val recursos = viewModel.recursos.observeAsState(listOf())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.moradoClaro))
            .padding(padding),
    )
    {
        LazyColumn {
            itemsIndexed(recursos.value) { _, recurso ->
                CardRecurso(recurso, navController, viewModel)
            }
        }
    }
}

@Composable
fun CardRecurso(recurso: Recurso, navController: NavHostController, viewModel: MainViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {

                navController.currentBackStackEntry?.savedStateHandle?.set("recurso", recurso)
                viewModel.getDataComentarios(recurso.id)
                navController.navigate(AppScreens.RecursoDetailScreen.route)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.morado),
            contentColor = Color.White),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        )
        {

            AsyncImage(
                model = recurso.url,
                contentDescription = "Imagen recurso",
                modifier = Modifier.size(100.dp)

                        .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = recurso.titulo,
                modifier = Modifier.weight(8f).fillMaxWidth(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

