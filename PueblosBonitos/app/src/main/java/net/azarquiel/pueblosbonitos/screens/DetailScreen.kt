package net.azarquiel.pueblosbonitos.screens

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import net.azarquiel.pueblosbonitos.model.Comunidad
import net.azarquiel.pueblosbonitos.model.Pueblowp
import net.azarquiel.pueblosbonitos.navegation.AppScreens
import net.azarquiel.pueblosbonitos.viewmodel.MainViewModel

@Composable
fun DetailScreen(navController: NavHostController, viewModel: MainViewModel) {
    val name = navController.previousBackStackEntry?.savedStateHandle?.get<Comunidad>("name")
    val pueblowp = viewModel.pueblowp.observeAsState()

    Scaffold(
        topBar = { DetailTopBar(navController,viewModel, pueblowp) },
        content = { padding ->

            if (name != null) {
                DetailContent(padding, navController, viewModel, pueblowp, name)
            }

        },
        floatingActionButton = {FloatingEstrellita2(viewModel, pueblowp) }
    )
}

@Composable
fun FloatingEstrellita2(viewModel: MainViewModel,  pueblowp: State<Pueblowp?>) {
    val colorEstrella by viewModel.colorEstrella.observeAsState(Color.Black)

    FloatingActionButton(
        onClick = { pueblowp.value?.let { viewModel.changeFavPueblo(it.pueblo) } },
        containerColor = Color.Blue,
        contentColor = Color.White
    ) {

        Icon(imageVector = Icons.Filled.Star,
            contentDescription = "",
            tint = colorEstrella
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(navController: NavHostController, viewModel: MainViewModel, pueblowp: State<Pueblowp?>) {
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
                Text(text = "${pueblowp.value?.pueblo?.nombrePueblo}")
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
fun DetailContent(
    padding: PaddingValues,
    navController: NavHostController,
    viewModel: MainViewModel,
    pueblowp: State<Pueblowp?>,
    name: Comunidad
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(Color.Cyan),

        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        AsyncImage(
            model = pueblowp.value?.pueblo?.imagen,
            contentDescription = "",
            modifier = Modifier.fillMaxWidth()


        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)

        ) {
            Text(text = "${pueblowp.value?.pueblo?.nombrePueblo}",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Blue)
                    .padding(10.dp),
                color = Color.Cyan,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,)
        }

        Text(text = "${pueblowp.value?.provincia?.nombreProvincia} (${name.nombreComunidad})",
            modifier = Modifier.padding(10.dp),
            color = Color.Blue,
            fontSize = 20.sp)
        Text(text = "mas...",
            modifier = Modifier.padding(10.dp)
                .fillMaxWidth()
                .clickable {
                    navController.currentBackStackEntry?.savedStateHandle?.set("link", pueblowp.value?.pueblo?.link)
                    navController.navigate(AppScreens.LinkScreen.route)
                },
            color = Color.Blue,
            textAlign = TextAlign.Center,
            fontSize = 20.sp)
    }
}