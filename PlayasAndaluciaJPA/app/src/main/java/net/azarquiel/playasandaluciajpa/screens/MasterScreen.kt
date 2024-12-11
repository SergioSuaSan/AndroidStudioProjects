package net.azarquiel.playasandaluciajpa.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import net.azarquiel.playasandaluciajpa.R
import net.azarquiel.playasandaluciajpa.model.Costa
import net.azarquiel.playasandaluciajpa.navegation.AppScreens
import net.azarquiel.playasandaluciajpa.viewmodel.MainViewModel

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

        {
            Image(
                painter = painterResource(id = R.drawable.andalucia_logo),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(fraction = .6F),


                contentScale = ContentScale.FillWidth

            )
        },
        colors = topAppBarColors(
            containerColor = Color(0xff09113e),
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

    val costas: List<Costa> by viewModel.costas.observeAsState(listOf())

    Column(
        modifier = Modifier
            .padding(padding)

            .background(Color(0xff09113e))
    ) {


        LazyColumn {


            items(costas) { costa ->
                CostasCard(navController, viewModel, costa)
            }


        }

    }
}

@Composable
fun CostasCard(

    navController: NavHostController,
    viewModel: MainViewModel,
    costa: Costa
) {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp),
        onClick = {
            navController.currentBackStackEntry?.savedStateHandle?.set("costa", costa)
            viewModel.getPlayabyCosta(costa.idCosta)
            navController.navigate(AppScreens.DetailScreen.route)


        }
    ) {
        Column(
            modifier = Modifier.background(Color.Cyan)
        ) {


            AsyncImage(
                model = costa.imagenCosta,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()

            )

            Text(
                text = costa.nombreCosta,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                fontSize = 30.sp,
                color = Color(0xff09113e),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                )
        }

    }


}




