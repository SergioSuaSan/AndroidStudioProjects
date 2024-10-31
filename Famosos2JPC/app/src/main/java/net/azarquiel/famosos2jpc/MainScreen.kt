package net.azarquiel.famosos2jpc

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
import androidx.compose.ui.graphics.Color
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
    val jugadaFotos = viewModel.jugadaFotos.observeAsState(intArrayOf(0,0,0,0,0))
    val jugadaNombres = viewModel.jugadaNombres.observeAsState(arrayOf("a", "b", "c", "d", "e"))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),)
    {
        // a pintaaarrrr sabiendo que estamos en column
        Row(modifier = Modifier.padding(16.dp)) {

            Column(modifier = Modifier.weight(2f)
                                    ) {
                for (i in 1..5) {
                    Row(modifier = Modifier.padding(16.dp)
                        .weight(1f)
                        .fillMaxSize()
                        ,
                        horizontalArrangement = Arrangement.Center

                        ) {
                        Image(
                            painter = painterResource(id = jugadaFotos.value[i-1] ),
                            contentScale = ContentScale.Fit,
                            contentDescription = "Famoso $i",
                            modifier = Modifier.clickable { viewModel.pulsado(i-1) }


                        )
                    }
                }
            }
            Column(modifier = Modifier.weight(3f)) {
                for (i in 1..5) {
                    Row(modifier = Modifier.padding(16.dp)
                        .weight(1f)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.LightGray)
                        .clickable { viewModel.pulsado(i-1) },
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

