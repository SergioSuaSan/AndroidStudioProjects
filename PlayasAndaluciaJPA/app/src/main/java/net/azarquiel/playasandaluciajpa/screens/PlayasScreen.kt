package net.azarquiel.playasandaluciajpa.screens



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import net.azarquiel.playasandaluciajpa.R
import net.azarquiel.playasandaluciajpa.model.Costa
import net.azarquiel.playasandaluciajpa.model.PlayawCosta
import net.azarquiel.playasandaluciajpa.viewmodel.MainViewModel


@Composable
fun PlayasScreen(navController: NavHostController, viewModel: MainViewModel) {
    val costa = navController.previousBackStackEntry?.savedStateHandle?.get<Costa>("costa")
    val azul by viewModel.azul.observeAsState(0)

    Scaffold(
        topBar = { PlayasTopBar(navController, viewModel, costa) },
        content = { padding ->
            PlayasContent(padding, navController, viewModel)
        },
        floatingActionButton = { FloatingBandera(viewModel, azul, costa!!) }

    )
}

@Composable
fun FloatingBandera(viewModel: MainViewModel, azul: Int, costa: Costa ) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FloatingActionButton(
            onClick = {

                viewModel.changeIconFilter()

                if (azul == 1) {
                    viewModel.getPlayabyCosta(costa.idCosta)
                } else {
                    viewModel.getFavPlayabyCosta(costa.idCosta)
                }
            },
            containerColor = Color(0xff09113e),
            contentColor = Color.White
            ,

            ) {
            if (azul == 0) {
                Image(
                    painter = painterResource(id = R.drawable.bnoazul),
                    contentDescription = "",
                    modifier = Modifier.padding(10.dp),

                    ) }
            else{
                Image(
                    painter = painterResource(id = R.drawable.bazul),
                    contentDescription = "",
                    modifier = Modifier.padding(10.dp),

                    )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayasTopBar(
    navController: NavHostController,
    viewModel: MainViewModel,
    costa: Costa?,
) {



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
                Text(text = "${costa?.nombreCosta}")



            }

        }
        ,
        colors = topAppBarColors(
            containerColor =Color(0xff09113e),
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}




@Composable
fun PlayasContent(
    padding: PaddingValues,
    navController: NavHostController,
    viewModel: MainViewModel,
) {

    val playasbycosta by viewModel.playasByCosta.observeAsState(listOf())
    var nombre = remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .padding(padding)
            .background(Color(0xff09113e)),
    )
    {


        TextField(
            value = nombre.value,
            onValueChange = {
                nombre.value = it

            },
            label = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()

                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "",
                        modifier = Modifier.size(20.dp),


                        )
                    Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                    Text("Busca Playa")

                }

            },
            placeholder = { Text("Buscar") },
            modifier = Modifier.fillMaxWidth(),

            )







        LazyColumn(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
        ) {
            items(playasbycosta.filter {
                it.playa.nombrePlaya.contains(nombre.value.text, true)
            }) { playaDatos ->
                // Log.d("Sergio","DentroDelItems: $puebloDatos")
                PlayaCard(navController, viewModel, playaDatos)


            }

        }
    }
}


@Composable
fun PlayaCard(navController: NavHostController, viewModel: MainViewModel, playaDatos: PlayawCosta) {
    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp),


        ) {
        Column(
            modifier = Modifier.background(Color(0xff09113e))
        ) {


            AsyncImage(
                model = playaDatos.playa.imagenPlaya,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                   ,
                contentScale = ContentScale.FillWidth

            )
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = playaDatos.playa.nombrePlaya,
                    modifier = Modifier.weight(7f)
                        .padding(10.dp),
                    fontSize = 30.sp,
                    color = Color.Cyan,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold

                    )
                if (playaDatos.playa.azul == 1) {
                   Image(
                       painter = painterResource(id = R.drawable.bazul),
                       contentDescription = "",
                       modifier = Modifier.fillMaxSize()
                           .weight(3f)
                           .padding(10.dp)
                   )
                }
            }


        }



    }
}

