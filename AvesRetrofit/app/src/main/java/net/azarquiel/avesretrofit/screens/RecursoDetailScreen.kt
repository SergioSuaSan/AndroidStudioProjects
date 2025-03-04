package net.azarquiel.avesretrofit.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import net.azarquiel.avesretrofit.R
import net.azarquiel.avesretrofit.model.Comentario
import net.azarquiel.avesretrofit.model.Recurso
import net.azarquiel.avesretrofit.model.Zona
import net.azarquiel.avesretrofit.viewmodel.MainViewModel




@Composable
fun RecursoDetailScreen(navController: NavHostController, viewModel: MainViewModel) {
    val zona = navController.previousBackStackEntry?.savedStateHandle?.get<Zona>("zona")
    val recurso = navController.previousBackStackEntry?.savedStateHandle?.get<Recurso>("recurso")
    Log.d("TAG", "RecursoDetailScreen: $recurso")
    Scaffold(
        topBar = { RecursoDetailTopBar(navController,viewModel) },
        content = { padding ->
            RecursoDetailContent(padding, navController, viewModel, zona, recurso)
        },
        floatingActionButton = { FloatingInsertar(viewModel) },

    )
}


@Composable
fun FloatingInsertar(viewModel: MainViewModel) {
    FloatingActionButton(
        onClick = {
            // Acción al hacer clic en el botón flotante
            viewModel.setDialog(true)

        },
        containerColor = Color.Blue,
        contentColor = Color.White
    ) {
        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = "Estrellita",
            tint = Color.White
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecursoDetailTopBar(navController: NavHostController, viewModel: MainViewModel) {
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
                Text(text = "Detalles")
            }
        },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        )

    )

}


@Composable
fun RecursoDetailContent(
    padding: PaddingValues,
    navController: NavHostController,
    viewModel: MainViewModel,
    zona: Zona?,
    recurso: Recurso?
) {
    val comentarios = viewModel.comentarios.observeAsState(listOf())
    AlertDialogSample(viewModel, recurso)

    Column(
        modifier = Modifier
            .background(colorResource(R.color.moradoClaro))
            .verticalScroll(rememberScrollState())
            .padding(padding),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,

    )
    {
        Log.d("TAG", "RecursoDetailContentColumn: $recurso")
        if (recurso != null) {
            Text( text = recurso.titulo,
                fontSize = 30.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.background(colorResource(R.color.morado)).fillMaxWidth(),
            )
            AsyncImage(
                model = recurso.url,
                contentDescription = "Imagen recurso",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            comentarios.value.forEach { comentario ->
                ComentarioCard(comentario, viewModel)
            }
        }
    }
}

@Composable
fun AlertDialogSample(viewModel: MainViewModel, recurso: Recurso?) {
    MaterialTheme {
        Column {
            val openDialog = viewModel.openDialog.observeAsState(false)
            val msg = viewModel.msg.observeAsState("")
            val usuario by viewModel.usuario.observeAsState()
            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = { // Si pulsamos fuera
                        viewModel.setDialog(false)
                    },
                    title = {
                        Text(text = "Comentario")
                    },
                    text = {

                            TextField(
                                value = msg.value,
                                onValueChange = { viewModel.saveComentario(it) },
                                label = { Text("Escribe tu comentario") }
                            )

                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                val comentario = Comentario(-1,
                                    usuario?.nick ?: "1",
                                    recurso!!.id,
                                    "04-03-2025",
                                    msg.value
                                )

                                    Log.d("TAG", "AlertDialogSample: $comentario, $recurso, $usuario")
                                if (usuario != null) {
                                    viewModel.insertarComentario(recurso.id, 13, comentario.fecha, comentario.comentario)
                                }
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
fun ComentarioCard(comentario: Comentario, viewModel: MainViewModel) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
           ,
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
                text = comentario.fecha,
                modifier = Modifier.weight(8f).fillMaxWidth(),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Text(
                text = comentario.usuario,
                modifier = Modifier.weight(2f).fillMaxWidth(),
                fontSize = 14.sp,

            )
        }

        Text(
            text = comentario.comentario,
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,

        )
    }
}
