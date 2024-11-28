package net.azarquiel.acbroomjpc.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import net.azarquiel.acbroomjpc.model.Equipo
import net.azarquiel.acbroomjpc.viewmodel.MainViewModel


@Composable
fun EquipoScreen(navController: NavHostController, viewModel: MainViewModel) {
    val equipo = navController.previousBackStackEntry?.savedStateHandle?.get<Equipo>("equipo")

    Scaffold(
        topBar = { EquipoTopBar(equipo) },
        content = { padding ->
            EquipoContent(padding, viewModel, navController, equipo)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EquipoTopBar(equipo: Equipo?) {
    TopAppBar(

        title = {
            equipo?.let {Text(text = equipo.nombre )} },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}


@Composable
fun EquipoContent(
    padding: PaddingValues,
    viewModel: MainViewModel,
    navController: NavHostController,
    equipo: Equipo?
) {
    equipo?.let {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),)
    {
        // a pintaaarrrr sabiendo que estamos en column
        Text(text = equipo.nombre)

        AsyncImage(
            model = equipo.imgescudo,
            contentDescription = "Jugador  Image",
            modifier = Modifier.fillMaxWidth(),
            alignment = Alignment.TopEnd,
            contentScale = ContentScale.FillWidth

        )
        AsyncImage(
            model = equipo.imgestadio,
            contentDescription = "Jugador  Image",
            modifier = Modifier.fillMaxWidth(),
            alignment = Alignment.TopEnd,
            contentScale = ContentScale.FillWidth

        )
    }
    }
}
