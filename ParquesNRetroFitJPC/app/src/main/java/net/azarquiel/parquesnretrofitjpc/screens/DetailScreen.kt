package net.azarquiel.parquesnretrofitjpc.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import net.azarquiel.parquesnretrofitjpc.R
import net.azarquiel.parquesnretrofitjpc.model.Parques
import net.azarquiel.parquesnretrofitjpc.viewmodel.MainViewModel


@Composable
fun DetailScreen(navController: NavHostController, viewModel: MainViewModel) {
    val parque = navController.previousBackStackEntry?.savedStateHandle?.get<Parques>("parque")

    Scaffold(
        topBar = { DetailTopBar(navController, parque) },
        content = { padding ->
            DetailContent(padding, viewModel, parque)
        },
        floatingActionButton = {
            fab(parque, viewModel)
        }
    )
}

@Composable
fun fab(parque: Parques?, viewModel: MainViewModel) {

    var like = viewModel.likes.observeAsState(parque?.likes)

    FloatingActionButton(
        onClick = {
            if (parque != null) {
                viewModel.sumaFav(parque)

            }
        }
    ) {
        Image(
            painter = painterResource(R.drawable.thumbs),
            contentDescription = null
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(navController: NavHostController, parque: Parques?) {

    TopAppBar(
        title = { parque?.let {
            Row (
                modifier = Modifier.padding(16.dp),
                verticalAlignment = CenterVertically

            ) {
                Text(
                    text = it.nombre,
                    modifier = Modifier.weight(7f)

                )
                Row (
                    verticalAlignment = CenterVertically,

                    modifier = Modifier.weight(2f).padding(8.dp)
                ){
                    Text(
                        text = it.likes.toString()
                    )
                    Image(
                        painter = painterResource(R.drawable.thumbs),
                        contentDescription = null
                    )
                }


            }

        } },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}
@Composable
fun DetailContent(padding: PaddingValues, viewModel: MainViewModel, parque: Parques?) {
    val imagenes = viewModel.imagenes.observeAsState(listOf())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),)
    {
        // a pintaaarrrr sabiendo que estamos en column
        if (parque != null) {




            Text(
                AnnotatedString.fromHtml(
                   parque.descripcion
                )
            )


            Box(
                modifier = Modifier.fillMaxSize()


            ) {

                AsyncImage(
                    model = parque.fondo,
                    contentDescription = null,
                    modifier = Modifier
                        .alpha(0.5f)
                        .fillMaxHeight(),
                    contentScale = Crop

                )

                Column {

                    LazyRow (
                        modifier = Modifier.padding(16.dp)
                    ){
                        items(imagenes.value) {
                            AsyncImage(
                                model = it.foto,
                                contentDescription = it.id.toString(),
                                modifier = Modifier
                                    .height(200.dp)
                                    .padding(8.dp)
                            )
                        }
                    }

                    AsyncImage(
                        model = parque.mapa,
                        contentDescription = null,
                        modifier = Modifier
                            .size(200.dp).padding(16.dp).align(Alignment.End),

                    )
                }


            }


        }
    }
}


