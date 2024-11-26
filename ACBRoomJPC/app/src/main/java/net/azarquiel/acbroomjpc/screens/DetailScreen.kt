package net.azarquiel.acbroomjpc.screens

import android.util.Log
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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ThumbUp
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import net.azarquiel.acbroomjpc.R
import net.azarquiel.acbroomjpc.model.Jugador
import net.azarquiel.acbroomjpc.model.JugadorWE
import net.azarquiel.acbroomjpc.viewmodel.MainViewModel


@Composable
fun DetailScreen(navController: NavHostController, viewModel: MainViewModel) {
    Scaffold(
       /* topBar = { DetailTopBar() },*/
        content = { padding ->
            DetailContent(padding, viewModel, navController)
        }
    )
}


/*@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar() {
    TopAppBar(
        title = { Text(text = "Title") },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}
*/

@Composable
fun DetailContent(
    padding: PaddingValues,
    viewModel: MainViewModel,
    navController: NavHostController
) {
    val jugadorwe = navController.previousBackStackEntry?.savedStateHandle?.get<JugadorWE>("jugadorwe")

    jugadorwe?.let {

    Column(
        modifier = Modifier.fillMaxSize()

            .padding(padding),)
    {
        // a pintaaarrrr sabiendo que estamos en column
        Log.d("paco", jugadorwe.toString())

        AsyncImage(
            model = jugadorwe!!.jugador.imagen,
            contentDescription = "Jugador  Image",
            modifier = Modifier.fillMaxWidth(),
            alignment = Alignment.TopEnd,
            contentScale = ContentScale.FillWidth

        )

        Column (
            modifier = Modifier
                .padding(8.dp)




        ){
            Row (
                modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
                    .fillMaxWidth()

                   ,

                horizontalArrangement = Arrangement.Center
            ){
                Text( text = jugadorwe.jugador.nombre,
                    color = Color.White,
                    fontWeight = FontWeight(500),
                    fontSize = 24.sp,
                    modifier = Modifier.background(color = Color.Cyan).fillMaxWidth(),
                    textAlign = TextAlign.Center)
            }
            Row (
                modifier = Modifier.background(color = Color.LightGray)
                    .fillMaxWidth()

                ,

                horizontalArrangement = Arrangement.Center
            ){

                Text( text = jugadorwe.equipo.nombre, color = MaterialTheme.colorScheme.primary)
                Spacer( modifier = Modifier.width(10.dp))
                Icon(
                    Icons.Filled.Info
                    , contentDescription = "",
                    Modifier.clickable {  }

                )

            }
            Row (
                modifier = Modifier.background(color = Color.LightGray)
                    .fillMaxWidth()

                ,
                horizontalArrangement = Arrangement.Center
            ){
            Text( text = jugadorwe.jugador.pais, color = MaterialTheme.colorScheme.primary)
             }
            Row (
                modifier = Modifier.background(color = Color.LightGray)
                    .fillMaxWidth()


            ,

            horizontalArrangement = Arrangement.Center
            ){
                Row(
                    modifier = Modifier.background(color = Color.LightGray)
                        .fillMaxWidth()
                        .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)

                    ,

                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        Icons.Filled.Info
                        , contentDescription = "Info Icon",
                        Modifier.clickable {  }
                    )
                    Row {
                        Text( text =" ${jugadorwe.jugador.estatura} m - ${jugadorwe.jugador.edad} años", color = MaterialTheme.colorScheme.primary, textAlign = TextAlign.Center)

                    }


                    Row {
                        Spacer(modifier = Modifier.width(10.dp))
                        Text( text = jugadorwe.jugador.likes.toString(), color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(end = 8.dp))
                        Icon(
                            Icons.Filled.ThumbUp
                            , contentDescription = "Likes Icon",
                            Modifier.clickable {

                                viewModel.addLike(jugadorwe.jugador)
                            }
                        )
                    }
                }
                 }
        }
    }
}

/*
@Preview (showBackground = true)
@Composable
fun DetailScreenPreview() {
    //DetailScreen()
    showJugador(JugadorWE(Jugador(2,2,"Perico","","","", 0.2f, 10, 10)))
}
*/
@Composable
fun showJugador(jugadorwe: JugadorWE) {
    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.jugador),
            contentDescription = "Jugador  Image",
            modifier = Modifier.weight(1f),
            alignment = Alignment.TopEnd,
            contentScale = ContentScale.FillWidth

        )

    }
}
}

