package net.azarquiel.signstraficojpc

import android.app.Activity
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.azarquiel.signstraficojpc.mode.Signal
import androidx.compose.material3.Text as Text


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
        title = { Text(text = "Señales de Tráfico") },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}


@Composable
fun CustomContent(padding: PaddingValues, viewModel: MainViewModel) {
    val signBuscar = viewModel.signBuscar.observeAsState(Signal())
    val jugadaFotos = viewModel.jugadaFotos.observeAsState(intArrayOf(0,0,0,0))
    val intentos = viewModel.intentos.observeAsState(0)
    val aciertos = viewModel.aciertos.observeAsState(0)
    val color = viewModel.color.observeAsState()
    AlertDialogWinner(viewModel)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),)
    {
        // a pintaaarrrr sabiendo que estamos en column
        Row(modifier = Modifier
            .fillMaxHeight(0.1F)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Text(text = "Aciertos: ${aciertos.value}", fontSize = 20.sp)

            Text(text = "Intento: ${intentos.value}/10", fontSize = 20.sp)
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.5F),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5F)
                    .background(color.value!!),
            ){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,

                ) {
                    Text(text = "Pregunta!",
                        fontSize = 50.sp,
                        fontWeight = FontWeight(weight = 500),
                        textAlign = TextAlign.Center,
                    )
                }

                Row (
                    modifier = Modifier
                        .fillMaxSize()
                        ,
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,


                ){
                    Text(text = signBuscar.value.descripcion,
                        fontSize = 20.sp,
                        fontWeight = FontWeight(weight = 500),
                        textAlign = TextAlign.Center,
                    )
                }
            }


        }
        Row(

        ) {
            Column (
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.8F)
            ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly) {

                        Image(
                            contentScale = ContentScale.Fit,
                            contentDescription = "Imagen",
                            painter = painterResource(id = jugadaFotos.value[0]),
                            modifier = Modifier
                                .size(150.dp)
                                .padding(10.dp)
                                .clickable
                                {
                                    viewModel.onCLickSign(0)
                                },

                            )
                        Image(
                            contentScale = ContentScale.Fit,
                            contentDescription = "Imagen",
                            painter = painterResource(id = jugadaFotos.value!![1]),
                            modifier = Modifier
                                .size(150.dp)
                                .padding(10.dp)
                                .clickable
                                {
                                    viewModel.onCLickSign(1)
                                },

                            )






                    }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly) {

                    Image(
                        contentScale = ContentScale.Fit,
                        contentDescription = "Imagen",
                        painter = painterResource(id = jugadaFotos.value[2]),
                        modifier = Modifier
                            .size(150.dp)
                            .padding(10.dp)
                            .clickable
                            {
                                viewModel.onCLickSign(2)
                            },

                        )
                    Image(
                        contentScale = ContentScale.Fit,
                        contentDescription = "Imagen",
                        painter = painterResource(id = jugadaFotos.value[3]),
                        modifier = Modifier
                            .size(150.dp)
                            .padding(10.dp)
                            .clickable
                            {
                                viewModel.onCLickSign(3)
                            },

                        )






                }


            }
        }
    }
}



@Composable
fun AlertDialogWinner(viewModel: MainViewModel) {
    MaterialTheme {
        val context = LocalContext.current
        val openDialog = viewModel.openDialog.observeAsState(false)
        val msg = viewModel.msg.observeAsState("")
        if (openDialog.value) {
            AlertDialog(
                title = { Text(text = "GAME OVER") },
                text = { Text(msg.value) },
                onDismissRequest = {  // Si pulsamos fuera cierra
                    viewModel.setDialog(false)
                    viewModel.newGame()
                },
                confirmButton = {
                    Button(
                        onClick = {(context as Activity).finish() })
                    { Text("Exit") }
                },
                dismissButton = {
                    Button(
                        onClick = { viewModel.newGame()
                            viewModel.setDialog(false)})
                    { Text("New Game") }
                }
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen(MainViewModel(MainActivity()))
}
