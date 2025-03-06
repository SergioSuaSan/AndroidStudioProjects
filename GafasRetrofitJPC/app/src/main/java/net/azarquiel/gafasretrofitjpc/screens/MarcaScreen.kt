package net.azarquiel.gafasretrofitjpc.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import net.azarquiel.gafasretrofitjpc.R
import net.azarquiel.gafasretrofitjpc.model.Marca
import net.azarquiel.gafasretrofitjpc.navegation.AppScreens
import net.azarquiel.gafasretrofitjpc.view.MainActivity
import net.azarquiel.gafasretrofitjpc.viewmodel.MainViewModel
import androidx.compose.foundation.Image as Image


@Composable
fun MarcaScreen(navController: NavHostController, viewModel: MainViewModel) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = { MasterTopBar(viewModel,textState) },
        content = { padding ->
            MasterContent(padding, navController, viewModel, textState)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasterTopBar(viewModel: MainViewModel, textState: MutableState<TextFieldValue>) {

    TopAppBar(
        title = {
            Row {
                Text(
                    text = "Marcas",
                    modifier = Modifier

                        .align(Alignment.CenterVertically)
                    ,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,

                )
                SearchView(textState)

                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = "Logo",
                    modifier = Modifier.size(50.dp).clickable {
                        viewModel.setDialog(true)
                    }.align(Alignment.CenterVertically)
                    ,


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



@Composable
fun AlertDialogLogin(viewModel: MainViewModel) {
    MaterialTheme {
        Column {
            val openDialog = viewModel.openDialog.observeAsState(false)
            var nick by remember { mutableStateOf("") }
            var pass by remember { mutableStateOf("") }
            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = { // Si pulsamos fuera
                        viewModel.setDialog(false)
                    },
                    title = {
                        Text(text = "Login/Register")
                    },
                    text = {

                        Column {

                            TextField(
                                value = nick,
                                onValueChange = { nick = it },
                                label = {
                                    Row {

                                    Icon(
                                        imageVector = Icons.Default.AccountCircle,
                                        contentDescription = "Logo"
                                    )

                                    Text("Escribe tu nick")
                                    }
                                }
                            )
                            TextField(
                                value = pass,
                                onValueChange = { pass = it },
                                label = {
                                    Row {

                                    Icon(
                                        imageVector = Icons.Default.Lock,
                                        contentDescription = "Logo"
                                    )
                                    Text("Escribe tu pass") }
                                    }
                            )

                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                viewModel.login(nick, pass)
                                viewModel.setDialog(false)
                            })

                        {

                            Text("Confirm Button")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { viewModel.setDialog(false) })

                        {

                            Text("Dismiss Button")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun MasterContent(
    padding: PaddingValues,
    navController: NavHostController,
    viewModel: MainViewModel,
    textState: MutableState<TextFieldValue>
) {
    val marcas = viewModel.marcas.observeAsState(listOf())
    AlertDialogLogin(viewModel)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.moradoClaro))
            .padding(padding),
    )
    {
        Image(
            painter = painterResource(id = R.drawable.header),
            contentDescription = "Logo",
            modifier = Modifier.fillMaxWidth(),
            alignment = Alignment.Center,
            contentScale = ContentScale.FillWidth
        )



        LazyColumn {
            itemsIndexed(marcas.value.filter { it.nombre.contains(textState.value.text, ignoreCase = true) }) { _, marca ->
                CardMarca(marca, navController, viewModel)
            }
        }
    }
}

@Composable
fun CardMarca(marca: Marca, navController: NavHostController, viewModel: MainViewModel) {
    val usuario = viewModel.usuario.observeAsState()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                Log.d("TAG", "CardLinea: ${usuario.value}")
                navController.currentBackStackEntry?.savedStateHandle?.set("marca", marca)
                viewModel.getGafasbyMarca(marca.id)
                navController.navigate(AppScreens.GafasScreen.route)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.morado),
            contentColor = Color.White),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        )
        {

            AsyncImage(
                model = "http://www.ies-azarquiel.es/paco/apigafas/img/marcas/${marca.imagen}",
                contentDescription = "Imagen recurso",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    ,

            )



        }
    }
}

