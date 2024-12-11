package net.azarquiel.playasandaluciajpa.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import net.azarquiel.playasandaluciajpa.R
import net.azarquiel.playasandaluciajpa.model.Costa
import net.azarquiel.playasandaluciajpa.viewmodel.MainViewModel

@Composable
fun DetailScreen(navController: NavHostController, viewModel: MainViewModel) {
    val costa = navController.previousBackStackEntry?.savedStateHandle?.get<Costa>("costa")

    Scaffold(
        topBar = { DetailTopBar(navController,viewModel, costa) },
        content = { padding ->


                DetailContent(padding, navController, viewModel, costa)


        },

    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(navController: NavHostController, viewModel: MainViewModel, costa: Costa?) {
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
                Text(text = "${costa?.nombreCosta}")
            }

        }
        ,
        colors = topAppBarColors(
            containerColor = Color(0xff09113e),
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}
@Composable
fun DetailContent(
    padding: PaddingValues,
    navController: NavHostController,
    viewModel: MainViewModel,
    costa: Costa?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(Color.Cyan),

        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Column(
            modifier = Modifier.background(Color.Cyan)
        ) {



                AsyncImage(
                    model = costa?.imagenCosta,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()

                )



            if (costa != null) {
                Text(
                    text = costa.nombreCosta,
                    modifier = Modifier.fillMaxWidth()
                        .background(Color(0xff09113e))
                        .padding(20.dp),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Cyan,
                    textAlign = TextAlign.Center,

                    )

                Text(
                    text = costa.descriptionCosta,
                    modifier = Modifier.fillMaxWidth()

                        .padding(20.dp),
                    fontSize = 20.sp,
                    color = Color.Black,


                )



                Row (
                    modifier = Modifier.fillMaxSize()

                        .clickable {
                            navController.currentBackStackEntry?.savedStateHandle?.set("costa", costa)
                            navController.navigate("PlayasScreen")
                        }
                    ,
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.playasico),
                        contentDescription = "",
                        modifier = Modifier

                    )

                    Text(
                        text = "Playas...",
                        modifier = Modifier
                            .padding(20.dp),
                        fontSize = 30.sp,
                        color = Color(0xff09113e),

                        fontWeight = FontWeight.Bold

                    )
                }

            }

        }

    }
}