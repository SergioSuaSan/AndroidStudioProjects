package net.azarquiel.signstraficojpc

import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            Text(text = "Aciertos: 1", fontSize = 20.sp)

            Text(text = "Intentos: 1/10", fontSize = 20.sp)
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.5F),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Column {
                Text(text = "Pregunta!",
                    fontSize = 50.sp,
                    fontWeight = FontWeight(weight = 500)
                )
                Text(text = "asdlkjfaslkdfjaslkdfjaslkf",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(weight = 500)
                )
            }


        }
        Row(

        ) {
            Column (
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.8F)
            ) {
                for (fila in 0 until 2) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(10.dp)


                        ,
                        horizontalArrangement = Arrangement.SpaceEvenly) {
                        for (columna in 0 until 2) {

                                Image(
                                    contentScale = ContentScale.Fit,
                                    contentDescription = "Imagen",
                                    painter = painterResource(id = R.drawable.s01),
                                    modifier = Modifier.clickable {
                                        viewModel.checkAnswer()
                                    }

                                )





                        }
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
