package net.azarquiel.examen.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import net.azarquiel.examen.navigation.AppScreens
import net.azarquiel.examenplantilla.R
import net.azarquiel.examenplantilla.viewmodel.MainViewModel

@Composable
fun DetailsScreen(navController: NavHostController, viewModel: MainViewModel) {
//    val comunidad =
//        navController.previousBackStackEntry?.savedStateHandle?.get<Comunidad>("comunidad")

    Scaffold(
        topBar = { DetailsScreenTopBar() },
//        floatingActionButton = { CustomFAB(viewModel) },
        content = { padding ->
            DetailsScreenContent(padding, viewModel, navController)

        }


    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreenTopBar() {
    TopAppBar(
        title = {
            Row {
//                comunidad?.let { Text(text = "${it.nombreComunidad}") }
                Text(text = "soy la dos")
            }
        },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}


@Composable
fun DetailsScreenContent(
    padding: PaddingValues,
    viewModel: MainViewModel,
    navController: NavHostController
) {

    Row(
        modifier = Modifier.fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Siguiente pagina",
            color = colorResource(R.color.purple_500),
            modifier = Modifier

                .fillMaxWidth()
                .clickable {  navController.navigate("MasterScreen")  }
            ,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
    }
        Column(
            modifier = Modifier
              //  .fillMaxSize()
                .padding(padding)
                .background(color = colorResource(R.color.white))

        )
        {

//            comunidad?.let {
//                viewModel.getPueblos(it.idComunidad)
//                LazyColumn {
//                    items(pueblo.value!!) { item ->
//                        cardPueblos(navController , item, viewModel, comunidad)
//                    }
//                }
//            }
        }







}

//@Composable
//fun cardPueblos(
//    navController: NavHostController,
//    pueblo: PuebloWithProvincia,
//    viewModel: MainViewModel,
//    comunidad: Comunidad,
//) {
//    Card(
//
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(6.dp, 3.dp)
//            .height(150.dp)
//            .clickable {
//                navController.currentBackStackEntry?.savedStateHandle?.set("comunidad", comunidad)
//                navController.currentBackStackEntry?.savedStateHandle?.set("pueblo", pueblo)
//                navController.navigate("PueblosDetailScreen")
//            },
//        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
//        shape = RoundedCornerShape(16.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = colorResource(R.color.fondocard)
//        ),
//    ) {
//        Row(
//            modifier = Modifier.fillMaxHeight(),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//
//            AsyncImage(
//                model = pueblo.pueblo.imagen,
//                contentDescription = "pueblo",
//                modifier = Modifier.weight(1f),
//                contentScale = ContentScale.Fit
//            )
//            Column(
//                modifier = Modifier
//                    .weight(2f)
//                    .padding(end = 8.dp, top = 8.dp)
//                    .fillMaxHeight(),
//            ) {
//                Text(text = "${pueblo.pueblo.nombrePueblo}",
//                    color = colorResource(R.color.white),
//                    modifier = Modifier
//
//                        .fillMaxWidth()
//                        .background(MaterialTheme.colorScheme.primary)
//                    ,
//                    fontSize = 14.sp,
//                    textAlign = TextAlign.Center
//                )
//                Text(text = "${pueblo.provincia.nombreProvincia}",
//                    color = colorScheme.primary,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                    ,
//                    fontSize = 14.sp,
//                    textAlign = TextAlign.Center
//                )
//                Row (
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 10.dp),
//
//                    horizontalArrangement = Arrangement.End,
//                    verticalAlignment = Alignment.Bottom
//
//                ){
//                Icon(
//                    Icons.Filled.Star, // Ícono de estrella
//                    contentDescription = "Star Icon", // Descripción para accesibilidad
//                    tint = if (pueblo.pueblo.fav == 1) colorResource(R.color.amarillo) else colorResource(R.color.Gris), // Color del ícono
//                    modifier = Modifier.size(30.dp) // Tamaño del ícono
//                        .clickable { viewModel.changePuebloFav(pueblo) }
//                )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun CustomFAB(viewModel: MainViewModel) {
//    val colorfav = viewModel.colorfav.observeAsState()
//    FloatingActionButton(
//        onClick = { viewModel.changeFav()},
//    ) {
//        Icon(
//            Icons.Filled.Star, // Ícono de estrella
//            contentDescription = "Star Icon", // Descripción para accesibilidad
//            tint = colorResource(colorfav.value!!), // Color del ícono
//            modifier = Modifier.size(30.dp) // Tamaño del ícono
//        )
//    }
//}