package net.azarquiel.pueblosbonitos.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import net.azarquiel.pueblosbonitos.R
import net.azarquiel.pueblosbonitos.model.Comunidad
import net.azarquiel.pueblosbonitos.navegation.AppScreens
import net.azarquiel.pueblosbonitos.viewmodel.MainViewModel

@Composable
fun MasterScreen(navController: NavHostController, viewModel: MainViewModel) {
    Scaffold(
        topBar = { MasterTopBar() },
        content = { padding ->
            ;
            MasterContent(padding, navController, viewModel)
        },


    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasterTopBar() {
    TopAppBar(
        title = { Text(text = "Pueblos Bonitos") },
        colors = topAppBarColors(
            containerColor = Color.Blue,
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}

@Composable

fun MasterContent(
    padding: PaddingValues,
    navController: NavHostController,
    viewModel: MainViewModel
) {
    val comunidades by viewModel.comunidades.observeAsState(listOf())

    Column(
        modifier = Modifier.padding(padding)

            .background(Color.Blue)
    ) {



        LazyColumn {

            items(comunidades) { comunidad ->
                ComunidadesCard(comunidad, navController, viewModel)
            }


        }

    }
}

@Composable
fun ComunidadesCard(
    comunidad: Comunidad,
    navController: NavHostController,
    viewModel: MainViewModel
) {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp)

            ,
        onClick = {
            navController.currentBackStackEntry?.savedStateHandle?.set("name", comunidad)
            viewModel.clicadoComunidad(comunidad)
            navController.navigate(AppScreens.PueblosScreen.route)

        }
    ) {
        Row(
            modifier = Modifier.background(Color.Cyan)
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,


        ) {


            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier.weight(3f)
                    .size(100.dp)

            )


            Text(text = comunidad.nombreComunidad,
                modifier = Modifier
                    .weight(7f),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Color.Blue,
            )

        }
    }



}




