package net.azarquiel.pueblosbonitos.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import net.azarquiel.pueblosbonitos.model.Comunidad
import net.azarquiel.pueblosbonitos.model.Pueblowp
import net.azarquiel.pueblosbonitos.navegation.AppScreens
import net.azarquiel.pueblosbonitos.viewmodel.MainViewModel


@Composable
fun PueblosScreen(navController: NavHostController, viewModel: MainViewModel) {
    val name = navController.previousBackStackEntry?.savedStateHandle?.get<Comunidad>("name")
    val pueblosByComunidad by viewModel.pueblosByComunidad.observeAsState(listOf())
    var nombre = remember { mutableStateOf(TextFieldValue("")) }
    val fav by viewModel.fav.observeAsState(0)
    Scaffold(
        topBar = { PueblosTopBar(navController, viewModel, name, nombre) },
        content = { padding ->
            PueblosContent(padding, navController, viewModel, name, pueblosByComunidad,  nombre)
        },
        floatingActionButton = {FloatingEstrellita(viewModel, fav, name)}
    )
}
@Composable
fun FloatingEstrellita(viewModel: MainViewModel, fav: Int, name: Comunidad?, ) {
    FloatingActionButton(
        onClick = {
            if (name != null) {
                viewModel.changeIconFilter()
            }
            if (fav == 0) {
                viewModel.getFavPueblowp(name!!)
            } else {
                viewModel.getAllPueblowp(name!!)
            }
        },
        containerColor = Color.Blue,
        contentColor = Color.White
    ) {
        if (fav == 0) {
            Icon(imageVector = Icons.Filled.Star,
                contentDescription = "",
                tint = Color.Black
            ) }
        else{
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "",
                tint = Color.Yellow
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PueblosTopBar(
    navController: NavHostController,
    viewModel: MainViewModel,
    name: Comunidad?,
    nombre: MutableState<TextFieldValue>,
) {
    val pulsadoBuscar by viewModel.pulsadoBuscar.observeAsState(false)


    TopAppBar(
        title = {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
            ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "",
                Modifier.clickable {

                    navController.popBackStack()
                })
                Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                if (name != null) {
                    if (!pulsadoBuscar) {
                    Text(text = name.nombreComunidad)

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Center


                    ){
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "",
                            modifier = Modifier
                                .size(20.dp)
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.pulsadoBuscar()
                                },
                        )
                    }
                    }
                    else {
                        TextField(
                            value = nombre.value,
                            onValueChange = {
                                nombre.value = it

                            },
                            label = { Text("Busca Provincia") },
                            placeholder = { Text("Buscar") },
                            modifier = Modifier.weight(.8F),

                        )
                        Column (
                            modifier = Modifier.weight(.2F).height(height = 50.dp)
                                .padding(end = 10.dp),
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.Center

                        ){
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(20.dp)

                                    .fillMaxWidth()
                                    .clickable {
                                        viewModel.pulsadoBuscar()
                                    },

                                )
                        }


                    }
                }


            }

        }
        ,
        colors = topAppBarColors(
            containerColor = Color.Blue,
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}




@Composable
fun PueblosContent(
    padding: PaddingValues,
    navController: NavHostController,
    viewModel: MainViewModel,
    name: Comunidad?,
    pueblosByComunidad: List<Pueblowp>,
    nombre: MutableState<TextFieldValue>,
) {

    Column(
        modifier = Modifier
            .padding(padding)
            .background(Color.Blue),
    )
    {

       LazyColumn (
           modifier = Modifier
               .background(Color.Blue)
               .fillMaxSize()
       ) {
         //  Log.d("Sergio","PueblosContent: $pueblosByComunidad")
           items(pueblosByComunidad.filter {
               it.provincia.nombreProvincia.contains(nombre.value.text,true)
           }) { puebloDatos ->
              // Log.d("Sergio","DentroDelItems: $puebloDatos")
               if (name != null) {
                   PuebloCard(puebloDatos, navController, viewModel, name)
               }
           }
       }
    }
}
@Composable
fun PuebloCard(
    pueblo: Pueblowp,
    navController: NavHostController,
    viewModel: MainViewModel,
    name: Comunidad
) {
    Log.d("Sergio","PuebloCard: $pueblo")
    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp)

        ,

        onClick = {
            navController.currentBackStackEntry?.savedStateHandle?.set("name", name)
            viewModel.clicadoPueblo(pueblo.pueblo)
            navController.navigate(AppScreens.DetailScreen.route)
        }
    ) {
        Row(
            modifier = Modifier
                .background(Color.Cyan)
                .fillMaxWidth()

                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            ) {


            AsyncImage(
                model = pueblo.pueblo.imagen,
                contentDescription = "",
                modifier = Modifier.size(100.dp)


            )

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = pueblo.pueblo.nombrePueblo,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Blue),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    color = Color.Cyan,
                )
                Text(
                    text = pueblo.provincia.nombreProvincia,
                    modifier = Modifier.padding(5.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Color.Blue,
                )
                Row (
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp)

                ) {
                    if (pueblo.pueblo.fav == 0) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "",
                            modifier = Modifier.size(20.dp),
                            tint = Color.Black
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "",
                            modifier = Modifier.size(20.dp),
                            tint = Color.Yellow


                        )
                    }
                }
            }






        }
    }
}