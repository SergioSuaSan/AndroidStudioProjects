package net.azarquiel.parquesnretrofitjpc.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import net.azarquiel.parquesnretrofitjpc.R
import net.azarquiel.parquesnretrofitjpc.model.Comunidad
import net.azarquiel.parquesnretrofitjpc.viewmodel.MainViewModel


@Composable
fun MasterScreen(navController: NavHostController, viewModel: MainViewModel) {
    Scaffold(
        topBar = { MasterTopBar() },
        content = { padding ->
            MasterContent(padding, viewModel, navController)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasterTopBar() {
    TopAppBar(
        title = { Text(text = "Comunidades") },
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
    navController: NavHostController
) {
    val comunidades = viewModel.comunidades.observeAsState(listOf())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),)
    {
        // a pintaaarrrr sabiendo que estamos en column
        LazyColumn (
            modifier = Modifier.padding(16.dp)
        ){

            items(comunidades.value) {

                CardComunidad(it, navController, viewModel)
            }
        }
    }
}

@Composable
fun CardComunidad(comunidad: Comunidad, navController: NavHostController, viewModel: MainViewModel){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                navController.currentBackStackEntry?.savedStateHandle?.set("comunidad", comunidad)
                navController.navigate("ParquesScreen")
                viewModel.getParquesByComunidad(comunidad.id)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.purple_500),
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

            val imagen = LocalContext.current.resources.getIdentifier("c${comunidad.id}", "drawable", LocalContext.current.packageName)
            Image(
                painter = painterResource(imagen),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .weight(2f)
            )

            Text(
                text = comunidad.nombre,
                modifier = Modifier.weight(8f).fillMaxWidth(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
   // MasterScreen(MainViewModel())
}

