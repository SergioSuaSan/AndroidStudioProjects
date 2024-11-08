package net.azarquiel.famosos2jpc

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


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
        title = { Text(text = "Title") },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}


@Composable
fun CustomContent(padding: PaddingValues, viewModel: MainViewModel) {

    //variables que vamos a utilizar. Tienen que estar inicializadas con un valor.
    val jugadaFotos = viewModel.jugadaFotos.observeAsState(intArrayOf(0,0,0,0,0))
    val jugadaNombres = viewModel.jugadaNombres.observeAsState(arrayOf("a", "b", "c", "d", "e"))

    //Las mutableStateListOf son listas que se pueden modificar. Salen directamente del viewModel
    val coloresFotos = viewModel.coloresFotos
    val coloresNombres = viewModel.coloresNombres




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),)
    {
        // a pintaaarrrr sabiendo que estamos en column
        //Hacemos 1 fila con 2 columnas, una con las fotos y otra con los nombres
        Row(modifier = Modifier.padding(16.dp)) {

            //primer columna con las fotos
            Column(modifier = Modifier.weight(2f)
                                    ) {

                //hacemos 5 filas con 1 columna cada una
                for (i in 1..5) {

                    Row(modifier = Modifier.padding(16.dp)
                        .weight(1f)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp))
                        //Le colocamos el color de la lista de colores de las fotos
                        .background(coloresFotos[i-1])
                        ,
                        horizontalArrangement = Arrangement.Center

                        ) {
                        Image(
                            //Le asignamos la foto de la lista de fotos
                            painter = painterResource(id = jugadaFotos.value[i-1] ),
                            contentScale = ContentScale.Fit,
                            contentDescription = "Famoso $i",
                            modifier = Modifier.clickable {
                                viewModel.pulsado1(i-1)
                                Log.d("pulsado", "pulsado $i")
                            }

                        )
                    }
                }
            }
            Column(modifier = Modifier.weight(3f)) {
                //segunda columna con los nombres
                for (i in 1..5) {
                    Row (modifier = Modifier.padding(6.dp)
                        .weight(1f)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { viewModel.pulsado2(i-1) }
                        //Le colocamos el color de la lista de colores de los nombres
                        .background(coloresNombres[i-1])
                        ,
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,

                    ) {
                        Text(
                            text = jugadaNombres.value[i-1],
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.headlineMedium


                        )
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen(MainViewModel(MainActivity()))
}

