package net.azarquiel.gafasretrofitjpc.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import net.azarquiel.gafasretrofitjpc.R
import net.azarquiel.gafasretrofitjpc.model.Gafa
import net.azarquiel.gafasretrofitjpc.model.Marca
import net.azarquiel.gafasretrofitjpc.navegation.AppScreens
import net.azarquiel.gafasretrofitjpc.viewmodel.MainViewModel


@Composable
fun GafasScreen(navController: NavHostController, viewModel: MainViewModel) {
    val marca = navController.previousBackStackEntry?.savedStateHandle?.get<Marca>("marca")
    Log.d("TAG", "GafasScreen: $marca")
    Scaffold(
        topBar = { GafasTopBar(marca, navController, viewModel) },
        content = { padding ->
            GafasContent(padding, navController, viewModel, marca)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GafasTopBar(marca: Marca?, navController: NavHostController, viewModel: MainViewModel) {
    val usuario = viewModel.usuario.observeAsState()
    TopAppBar(
        title = {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
            ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "",
                    Modifier.clickable {
                        navController.popBackStack()
                    })
                Spacer(modifier = Modifier.padding(horizontal = 5.dp))

                if (marca != null) {
                    Text(
                        text = marca.nombre,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,

                        )
                }

                usuario.value?.nick?.let {
                    Text(
                        text = it,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth().padding(end = 20.dp),
                        textAlign = TextAlign.End


                    )
                }

            }


            },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}


@Composable
fun GafasContent(
    padding: PaddingValues,
    navController: NavHostController,
    viewModel: MainViewModel,
    marca: Marca?
) {
    val gafas = viewModel.gafas.observeAsState(listOf())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.moradoClaro))
            .padding(padding),
    )
    {
        Log.d("TAG", "Estoy en Content: ${gafas.value}")
        LazyColumn {
            itemsIndexed(gafas.value) { _, gafa ->
                Log.d("TAG", "Estoy en Lacy: $gafa")
                CardRecurso(gafa, navController, viewModel, marca)
            }
        }
    }
}

@Composable
fun CardRecurso(
    gafa: Gafa,
    navController: NavHostController,
    viewModel: MainViewModel,
    marca: Marca?
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {

                navController.currentBackStackEntry?.savedStateHandle?.set("gafa", gafa)
                Log.d("TAG", "Voy a guardar: $gafa")
                navController.currentBackStackEntry?.savedStateHandle?.set("marca", marca)
                Log.d("TAG", "Voy a guardar: $marca")
                viewModel.getDataComentarios(gafa.id)
                navController.navigate(AppScreens.DetailScreen.route)
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
                model = "http://www.ies-azarquiel.es/paco/apigafas/img/gafas/${gafa.imagen}",
                contentDescription = "Imagen recurso",
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))

                    .height(100.dp),
                contentScale = ContentScale.Fit,


            )

            Text(
                text = gafa.nombre,
                modifier = Modifier.weight(8f).fillMaxWidth(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

