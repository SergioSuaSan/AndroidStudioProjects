package net.azarquiel.avesretrofit.screens

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import net.azarquiel.avesretrofit.R
import net.azarquiel.avesretrofit.model.Zona
import net.azarquiel.avesretrofit.navegation.AppScreens
import net.azarquiel.avesretrofit.viewmodel.MainViewModel




@Composable
fun DetailScreen(navController: NavHostController, viewModel: MainViewModel) {
    val zona = navController.previousBackStackEntry?.savedStateHandle?.get<Zona>("zona")
    Scaffold(
        topBar = { DetailTopBar(navController,viewModel) },
        content = { padding ->
            DetailContent(padding, navController, viewModel, zona)
        },
        floatingActionButton = { FloatingEstrellita(zona, viewModel, navController) },
        floatingActionButtonPosition = FabPosition.Center
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(navController: NavHostController, viewModel: MainViewModel) {
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
                Text(text = "Zona")
            }
        },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        )

    )

}
@Composable
fun FloatingEstrellita(zona: Zona?, viewModel: MainViewModel, navController: NavHostController) {
    FloatingActionButton(
        onClick = {
            if (zona != null) {
                viewModel.getDataRecursos(zona.id)
                navController.currentBackStackEntry?.savedStateHandle?.set("zona", zona)
                navController.navigate(AppScreens.RecursosScreen.route)
            }

        },
        containerColor = colorResource(R.color.morado),
        contentColor = Color.White
    ) {
        Icon(
            imageVector = Icons.Outlined.Info,
            contentDescription = "Estrellita",
            tint = Color.White
        )
    }
}

@Composable
fun DetailContent(
    padding: PaddingValues,
    navController: NavHostController,
    viewModel: MainViewModel,
    zona: Zona?
) {
    Column(
        modifier = Modifier
            .background(colorResource(R.color.moradoClaro))
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(padding),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,

    )
    {
        if (zona != null) {
            Text( text = zona.nombre,
                fontSize = 30.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.background(colorResource(R.color.morado)).fillMaxWidth(),
            )
            Text( text = "${zona.localizacion} (${zona.geom_lat}, ${zona.geom_lon})",
                fontSize = 20.sp)
            Text( text = "Formaciones Principales",
                fontSize = 30.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.background(colorResource(R.color.morado)).fillMaxWidth() )
            Text( text = zona.formaciones_principales,
                fontSize = 20.sp)
            Text( text = "Presentacion",
                fontSize = 30.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.background(colorResource(R.color.morado)).fillMaxWidth() )
            Text( AnnotatedString.fromHtml(
                    zona.presentacion,
                ),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}
