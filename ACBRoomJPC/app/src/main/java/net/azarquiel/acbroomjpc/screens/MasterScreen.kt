package net.azarquiel.acbroomjpc.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import net.azarquiel.acbroomjpc.R
import net.azarquiel.acbroomjpc.model.Equipo
import net.azarquiel.acbroomjpc.model.Jugador
import net.azarquiel.acbroomjpc.model.JugadorWE
import net.azarquiel.acbroomjpc.viewmodel.MainViewModel


@Composable
fun MasterScreen(navController: NavHostController, viewModel: MainViewModel) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Scaffold(
        topBar = { MasterTopBar(textState,viewModel) },
        content = { padding ->
            MasterContent(padding, viewModel, textState, navController)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasterTopBar(textState: MutableState<TextFieldValue>, viewModel: MainViewModel) {
    val idIcoFilter by viewModel.idIcoFilter.observeAsState(R.drawable.player)

    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "ACB",
                    Modifier.padding(end = 10.dp)
                )
                SearchView(textState)
                Icon(painter = painterResource(idIcoFilter),
                    contentDescription = "Player Icon",
                    modifier = Modifier.size(40.dp)
                        .clickable { viewModel.changeIcoFilter() },
                    )
            }
        },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}


@Composable
fun MasterContent(
    padding: PaddingValues,
    viewModel: MainViewModel,
    textState: MutableState<TextFieldValue>,
    navController: NavHostController
) {
    val jugadores = viewModel.jugadores.observeAsState(listOf())
    val isPlayer by viewModel.isPlayer.observeAsState(true)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),)
    {
        LazyColumn(
            Modifier.background(colorResource(R.color.azuloscuro)),
        ) {
            items(jugadores.value.filter {
                if (isPlayer) {
                    it.jugador.nombre.contains(textState.value.text, ignoreCase = true)
                } else {
                    it.equipo.nombre.contains(textState.value.text, ignoreCase = true)
                }
            }) { item ->
                JugadorCard(item, navController)
            }
        }

    }
}

@Composable
fun JugadorCard(jugadorwe: JugadorWE, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp, 3.dp)
            .height(150.dp)
            .clickable {
                navController.currentBackStackEntry?.savedStateHandle?.set("jugadorwe", jugadorwe)
                navController.navigate("DetailScreen")
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.azulclaro),
            contentColor = colorResource(R.color.azuloscuro)),
    ) {
        Row(
            modifier = Modifier.fillMaxHeight()
        ) {
            AsyncImage(
                model = jugadorwe.jugador.imagen,
                contentDescription = "Jugador  Image",
                modifier = Modifier.weight(1f),
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier.weight(2f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = jugadorwe.jugador.nombre,
                    color = colorResource(R.color.azulclaro),
                    modifier = Modifier
                        .background(colorResource(R.color.azuloscuro))
                        .fillMaxWidth()
                        ,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(Icons.Filled.ThumbUp, contentDescription = "Likes")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = jugadorwe.jugador.likes.toString(),
                        fontSize = 22.sp,
                        modifier = Modifier
                        .padding(end = 8.dp))
                }
                Text(
                    text = jugadorwe.jugador.pais,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                )
                Text(
                    text = jugadorwe.equipo.nombre,
                    modifier = Modifier.fillMaxWidth()
                        .padding(end = 8.dp, bottom = 8.dp),
                    textAlign = TextAlign.End,
                    )

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
    state: MutableState<TextFieldValue>
) {
    TextField(
        value = state.value,
        onValueChange = {value->
            state.value = value
        },
        modifier = Modifier
            .padding(2.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(2.dp, Color.DarkGray, RoundedCornerShape(20.dp)),
        placeholder = {
            Text(text = "Search here...")
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedPlaceholderColor = Color.White,
        ),
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Search Icon") },
        maxLines = 1,
        singleLine = true,
        textStyle = TextStyle(
            color = Color.Black, fontSize = 20.sp
        )
    )
}


