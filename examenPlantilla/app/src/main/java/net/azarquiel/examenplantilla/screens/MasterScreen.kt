package net.azarquiel.examen.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import net.azarquiel.examenplantilla.R
import net.azarquiel.examenplantilla.viewmodel.MainViewModel


@Composable
fun MasterScreen(navController: NavHostController, viewModel: MainViewModel) {
    Scaffold(
        topBar = { MasterTopBar() },
        content = { padding ->
            MasterContent(padding, viewModel,navController)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasterTopBar() {
    TopAppBar(
        title = { Text(text = "Comunidades") },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}


@Composable
fun MasterContent(
    padding: PaddingValues,
    viewModel: MainViewModel,
    navController: NavHostController,

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(color = colorResource(R.color.purple_200))

    )
    {
        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Siguiente pagina",
                color = colorResource(R.color.black),
                modifier = Modifier

                    .fillMaxWidth()
                    .clickable {  navController.navigate("DetailsScreen")  }
                ,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
        }

//        val comunidades = viewModel.comunidades.observeAsState(listOf())
//        LazyColumn {
//            items(comunidades.value) { item ->
//                CardComunidades(item, navController)
//            }
//        }
    }
}
//
//@Composable
//fun CardComunidades(comunidad: Comunidad, navController: NavHostController) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(6.dp, 3.dp)
//            .height(150.dp)
//            .clickable {
//                navController.currentBackStackEntry?.savedStateHandle?.set("comunidad", comunidad)
//                navController.navigate("PueblosScreen")
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
//            Image(
//                painterResource(R.drawable.logo),
//                contentDescription = "comunidad",
//                modifier = Modifier.weight(1f),
//                contentScale = ContentScale.FillBounds
//            )
//            Column(
//                modifier = Modifier.weight(2f)
//                    .padding(end = 8.dp, top = 8.dp)
//                    .fillMaxHeight(),
//                verticalArrangement = Arrangement.Center
//            ) {
//                Text(text = comunidad.nombreComunidad,
//                    color = colorResource(R.color.black),
//                    modifier = Modifier
//
//                        .fillMaxWidth()
//                    ,
//                    fontSize = 24.sp,
//                    textAlign = TextAlign.Center
//                )
//            }
//        }
//    }
//}
//
////@Preview(showBackground = true)
////@Composable
////fun DefaultPreview() {
////    MasterScreen(MainViewModel())
////}
