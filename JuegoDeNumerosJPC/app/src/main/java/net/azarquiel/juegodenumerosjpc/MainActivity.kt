package net.azarquiel.juegodenumerosjpc

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.azarquiel.juegodenumerosjpc.ui.theme.JuegoDeNumerosJPCTheme

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            JuegoDeNumerosJPCTheme {
                MainScreen(MyViewModel())
            }
        }
    }

    //@Preview(showBackground = true)
    @Composable
    private fun MainScreen(myViewModel: MyViewModel) {
        Scaffold(
            // Barra superior
            topBar = { CustomTopBar() },
            // Contenido principal
            content = { padding ->
                CustomContent(padding, myViewModel)
            }
        )
    }

    @Composable
    fun CustomContent(padding: PaddingValues, myViewModel: MyViewModel) {
        val intentillo by myViewModel.intentos.observeAsState(0)
        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),

            horizontalArrangement = Arrangement.End
        ){

            Text(
                text = "Intentos: $intentillo",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)

            )
        }

        Column(
            // Modificadores de estilo de la columna
            modifier = Modifier
                // Ocupar todo el espacio disponible
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            // Contenido de la aplicación
            content = {

                Contenido(myViewModel, padding)

            }
        )
    }

    @Composable
    fun Contenido(myViewModel: MyViewModel, padding: PaddingValues) {
        var n = rememberSaveable { mutableIntStateOf(50)}

        var openDialog = remember { mutableStateOf(false) }
        AlertDialogSample(padding, myViewModel, openDialog)

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = n.value.toString(),
                fontSize = 100.sp,
                fontWeight = FontWeight.Bold,
            )
            Column(
                modifier = Modifier

                    .size(
                        width = 500.dp,
                        height = 400.dp
                    ),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Button(
                        modifier = Modifier.size(100.dp),
                        onClick = {
                            n.value--
                            myViewModel.incrementarIntentos()
                            comparacion(n, myViewModel, openDialog)
                        },
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.purple_700))
                    ) {
                        Text("-1")
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Button(
                        modifier = Modifier.size(100.dp),
                        onClick = {
                            n.value++
                            myViewModel.incrementarIntentos()
                            comparacion(n, myViewModel, openDialog)
                        },
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.purple_700))
                    ) {
                        Text("+1")
                    }

                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Button(
                        modifier = Modifier.size(100.dp),
                        onClick = {
                            n.value -= 5
                            myViewModel.incrementarIntentos()
                            comparacion(n, myViewModel, openDialog)
                        },
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.purple_700))
                    ) {
                        Text("-5 ")
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Button(
                        modifier = Modifier.size(100.dp),
                        onClick = {
                            n.value += 5
                            myViewModel.incrementarIntentos()
                            comparacion(n, myViewModel, openDialog)
                        },
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.purple_700))
                    ) {
                        Text("+5")
                    }

                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Button(
                        modifier = Modifier.size(100.dp),
                        onClick = {
                            n.value -= 10
                            myViewModel.incrementarIntentos()
                            comparacion(n, myViewModel, openDialog)
                        },
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.purple_700))
                    ) {
                        Text("-10")
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Button(
                        modifier = Modifier.size(100.dp),
                        onClick = {
                            n.value += 10
                            myViewModel.incrementarIntentos()
                            comparacion(n, myViewModel, openDialog)
                        },
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.purple_700))
                    ) {
                        Text("+10")
                    }

                }
            }
        }
    }

    private fun comparacion(
        n: MutableIntState,
        myViewModel: MyViewModel,
        openDialog: MutableState<Boolean>
    ) {
        if (n.value < myViewModel.numeroAleatorio.value!!) {
            Toast.makeText(this, "El número es mayor", Toast.LENGTH_SHORT).show()
        } else if (n.value > myViewModel.numeroAleatorio.value!!) {
            Toast.makeText(this, "El número es menor", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Has acertado", Toast.LENGTH_SHORT).show()

            openDialog.value = true

            n.intValue = 50
        }
    }

    @Composable
    fun AlertDialogSample(innerPadding: PaddingValues, myViewModel: MyViewModel, openDialog: MutableState<Boolean>) {
        MaterialTheme {
            Column {
                //val openDialog = remember { mutableStateOf(false)  }
                if (openDialog.value) {
                    AlertDialog(
                        onDismissRequest = {  // Si pulsamos fuera
                            openDialog.value = false
                        },
                        title = {
                            Text(text = "FELISITASIONES")
                        },
                        text = {
                            Text("Lo ha conseguido en ${myViewModel.intentos.value} intentos")
                        },
                        confirmButton = {
                            Button(
                                onClick = { openDialog.value = false
                                    myViewModel.reiniciarJuego()})
                            {
                                Text("Confirm Button")
                            }
                        },

                    )
                }
            }
        }
    }





    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CustomTopBar() {
        TopAppBar(
            // Título de la barra superior
            title = { Text(text = "Adivina el número", textAlign = TextAlign.Center) },
            colors = topAppBarColors(
                containerColor = colorResource(R.color.purple_700),
                titleContentColor = colorResource(R.color.white),
            )
        )
    }
}


