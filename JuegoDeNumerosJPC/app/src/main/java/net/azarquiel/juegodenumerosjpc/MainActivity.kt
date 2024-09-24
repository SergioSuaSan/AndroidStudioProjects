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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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


    @Composable
    private fun MainScreen(myViewModel: MyViewModel) {
        Scaffold(
            // Barra superior
            topBar = { CustomTopBar() },
            // Contenido principal
            content = { padding ->
                CustomContent(padding, )

            }
        )
    }

    @Composable
    fun CustomContent(padding: PaddingValues) {

        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),

            horizontalArrangement = Arrangement.End
        ){

            Text(
                text = "Intentos: ",
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

                Contenido(numeroAleatorio)

            }
        )
    }

    @Composable
    fun Contenido(numeroAleatorio: Int) {
        var n by rememberSaveable { mutableStateOf(50)}



        Text(
            text = n.toString(),
            fontSize = 100.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 40.dp)
        )
        Column(
            modifier = Modifier

                .size(
                    width = 300.dp,
                    height = 250.dp
                ),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                    onClick = { n--; this@MainActivity.contador++; comparacion(n, numeroAleatorio) },
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.purple_700))
                ) {
                    Text("-1")
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = { n++; this@MainActivity.contador++; comparacion(n, numeroAleatorio)  },
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.purple_700))
                ) {
                    Text("+1")
                }

            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                    onClick = { n-=5; this@MainActivity.contador++; comparacion(n, numeroAleatorio)  },
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.purple_700))
                ) {
                    Text("-5 ${this@MainActivity.contador}")
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = { n+=5; this@MainActivity.contador++ ; comparacion(n, numeroAleatorio) },
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.purple_700))
                ) {
                    Text("+5")
                }

            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                    onClick = { n-=10; this@MainActivity.contador++; comparacion(n, numeroAleatorio)  },
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.purple_700))
                ) {
                    Text("-10")
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = { n+=10; this@MainActivity.contador++; comparacion(n, numeroAleatorio)  },
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.purple_700))
                ) {
                    Text("+10")
                }

            }
        }
    }

    private fun comparacion(n: Int, numeroAleatorio: Int) {
        if (n < this.numeroAleatorio) {
            Toast.makeText(this, "El número es mayor", Toast.LENGTH_SHORT).show()
        } else if (n > this.numeroAleatorio) {
            Toast.makeText(this, "El número es menor", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Has acertado", Toast.LENGTH_SHORT).show()        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CustomTopBar() {
        TopAppBar(
            // Título de la barra superior
            title = { Text(text = "Adivina el número", textAlign = TextAlign.Center, ) },
            colors = topAppBarColors(
                containerColor = colorResource(R.color.purple_700),
                titleContentColor = colorResource(R.color.white),
            )
        )
    }
}


