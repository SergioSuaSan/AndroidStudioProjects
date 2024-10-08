package net.azarquiel.blackjackjpc


import android.app.Dialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import net.azarquiel.blackjackjpc.MainViewModel
import net.azarquiel.blackjackjpc.model.Carta



@Composable
fun MainScreen(viewModel: MainViewModel) {
    Scaffold(
        topBar = { CustomTopBar() },
        content = { padding ->
            CustomContent(padding, viewModel)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar() {
    TopAppBar(
        title = { Text(text = "BlackJack") },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}


@Composable
fun CustomContent(padding: PaddingValues, viewModel: MainViewModel) {
    Box (
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.mesadepoker),
            contentDescription = "Fondo",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize(),
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.SpaceEvenly,
        content = {
            MainContent(viewModel)
        }
    )
}


@Composable
fun MainContent(viewModel: MainViewModel) {
    val cartas = viewModel.cartas


   //Aquí se pinta
   Row(
       modifier = Modifier
           .fillMaxWidth(),
       horizontalArrangement = Arrangement.Center,
   ){

       LazyRow {
           items(cartas) { carta ->
               CartaCard(carta, viewModel)


           }
       }
   }
    Row(
       modifier = Modifier
           .fillMaxWidth(),
       horizontalArrangement = Arrangement.Center,
   ){
    Image(

        painter = painterResource(id = R.drawable.mazo),
        contentDescription = "mazo",
        modifier = Modifier
            .clickable {
                viewModel.sacaCarta()
                if (viewModel.puntos > 21) {


                     }
            }
            .width(100.dp)
            .height(150.dp),

    )

   }
    Row(
       modifier = Modifier
           .fillMaxWidth(),
       horizontalArrangement = Arrangement.Center,
   ){
        Button(
            onClick = {

            },
            contentPadding = PaddingValues(16.dp),


        ) {
            Text(
                text = "Stop",
                fontSize = 20.sp,
            )
        }
   }
}

@Composable
fun CartaCard(carta: Carta, viewModel: MainViewModel) {
    val context = LocalContext.current
    val id = context.resources.getIdentifier("${viewModel.palos[carta.palo]}${carta.numero}", "drawable", context.packageName)
    Image(
        painter = painterResource(id),
        contentDescription = "Carta",
        modifier = Modifier
            .width(100.dp)
            .height(150.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen(MainViewModel())
}


