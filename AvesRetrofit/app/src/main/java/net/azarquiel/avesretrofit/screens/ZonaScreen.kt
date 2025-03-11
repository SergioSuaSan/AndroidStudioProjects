package net.azarquiel.avesretrofit.screens

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import net.azarquiel.avesretrofit.R
import net.azarquiel.avesretrofit.model.Zona
import net.azarquiel.avesretrofit.viewmodel.MainViewModel
import androidx.compose.foundation.Image as Image


@Composable
fun ZonaScreen(navController: NavHostController, viewModel: MainViewModel) {
    Scaffold(
        topBar = { MasterTopBar(viewModel) },
        content = { padding ->
            MasterContent(padding, navController, viewModel)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasterTopBar(viewModel: MainViewModel) {
    TopAppBar(
        title = {
            Row {
                Text(
                    text = "Categorias",
                    modifier = Modifier
                        .weight(7f)
                        .align(Alignment.CenterVertically)
                    ,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,

                )


                Image(
                    painter = painterResource(id = R.drawable.ic_account),
                    contentDescription = "Logo",
                    modifier = Modifier.size(50.dp).clickable {
                        viewModel.setDialog(true)
                    },
                    alignment = Alignment.CenterEnd,

                )
                Image(
                    painter = painterResource(id = R.drawable.ic_logout),
                    contentDescription = "Logo",
                    modifier = Modifier.size(50.dp).clickable {
                        viewModel.logout()
                        Log.d("TAG", "MasterTopBar: ${viewModel.usuario.value}")
                    },
                    alignment = Alignment.CenterEnd,
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
                        Text(text = "Login")
                    },
                    text = {

                        Column {

                            TextField(
                                value = nick,
                                onValueChange = { nick = it },
                                label = { Text("Escribe tu nick") }
                            )
                            TextField(
                                value = pass,
                                onValueChange = { pass = it },
                                label = { Text("Escribe tu pass") }
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
    viewModel: MainViewModel
) {
    val zonas = viewModel.zonas.observeAsState(listOf())
    AlertDialogLogin(viewModel)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.moradoClaro))
            .padding(padding),
    )
    {
        LazyColumn {
            itemsIndexed(zonas.value) { _, zona ->
                CardLinea(zona, navController, viewModel)
            }
        }
    }
}

@Composable
fun CardLinea(zona: Zona, navController: NavHostController, viewModel: MainViewModel) {
    val usuario = viewModel.usuario.observeAsState()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                Log.d("TAG", "CardLinea: ${usuario.value}")
                navController.currentBackStackEntry?.savedStateHandle?.set("zona", zona)
                navController.navigate("DetailScreen")
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


            Text(
                text = zona.nombre,
                modifier = Modifier.weight(8f).fillMaxWidth(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

