package net.azarquiel.gafasretrofitjpc.screens

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import androidx.compose.ui.platform.LocalContext
import net.azarquiel.gafasretrofitjpc.R
import net.azarquiel.gafasretrofitjpc.model.Comentario
import net.azarquiel.gafasretrofitjpc.model.Gafa
import net.azarquiel.gafasretrofitjpc.model.Marca
import net.azarquiel.gafasretrofitjpc.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailScreen(navController: NavHostController, viewModel: MainViewModel) {
    val context = LocalContext.current
    val marca = navController.previousBackStackEntry?.savedStateHandle?.get<Marca>("marca")
    Log.d("TAG", "Estoy enseñando: $marca")
    val gafa = navController.previousBackStackEntry?.savedStateHandle?.get<Gafa>("gafa")
    Log.d("TAG", "Estoy enseñando: $gafa")
    Scaffold(
        topBar = { RecursoDetailTopBar(navController, viewModel) },
        content = { padding ->
            RecursoDetailContent(padding, navController, viewModel, marca, gafa, context)
        },
        floatingActionButton = { FloatingInsertar(viewModel, context) },

        )
}


@Composable
fun FloatingInsertar(viewModel: MainViewModel, context: Context) {
    FloatingActionButton(
        onClick = {
            // Acción al hacer clic en el botón flotante
            if (viewModel.usuario.value != null) {
            viewModel.setDialog(true)
            } else{
                Toast.makeText(context, "Debes iniciar sesión", Toast.LENGTH_SHORT).show()
            }

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
    val usuario = viewModel.usuario.observeAsState()
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
                usuario.value?.nick?.let {
                    Text(
                        text = it,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth().padding(end = 20.dp),
                        textAlign = TextAlign.End


                    )
                }
            }
        },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        )

    )

}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RecursoDetailContent(
    padding: PaddingValues,
    navController: NavHostController,
    viewModel: MainViewModel,
    marca: Marca?,
    gafa: Gafa?,
    context: Context
) {
    val comentarios = viewModel.comentarios.observeAsState(listOf())
    AlertDialogSample(viewModel, gafa, context)

    Column(
        modifier = Modifier
            .background(colorResource(R.color.moradoClaro))
            .verticalScroll(rememberScrollState())
            .padding(padding),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,

        )
    {
        Log.d("TAG", "RecursoDetailContentColumn: $gafa")
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(R.color.morado))
                .padding(4.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(12.dp),
        ) {
            if (gafa != null) {
                AsyncImage(
                    model = "http://www.ies-azarquiel.es/paco/apigafas/img/gafas/${gafa.imagen}",
                    contentDescription = "Imagen gafa",
                    modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.FillWidth
                )
                Text(
                    text = gafa.nombre,
                    fontSize = 30.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(colorResource(R.color.morado))
                        .fillMaxWidth(),
                )

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(R.color.morado))
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        text = gafa.precio.toString() + "€",
                        fontSize = 20.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )

                    AsyncImage(
                        model = "http://www.ies-azarquiel.es/paco/apigafas/img/marcas/${marca?.imagen}",
                        contentDescription = "Imagen marca",
                        modifier = Modifier.padding(10.dp).clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Fit,

                    )
                }
            }

        }

        comentarios.value.forEach { comentario ->
            ComentarioCard(comentario, viewModel)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AlertDialogSample(viewModel: MainViewModel, gafa: Gafa?, context: Context) {
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

                                if (usuario != null && gafa != null) {
                                    val fechaDate = Date()
                                    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                                    //ChatGPT: Poner la zona horaria para que no me de una hora antes.
                                    sdf.timeZone = TimeZone.getTimeZone("Europe/Madrid")
                                    val fecha:String = sdf.format(fechaDate)

                                    viewModel.insertarComentario(gafa, usuario!!.idusuario, fecha, msg.value)

                                }
                                viewModel.setDialog(false)
                                Toast.makeText(context, "Comentario insertado", Toast.LENGTH_SHORT).show()
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
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.morado),
            contentColor = Color.White
        ),
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
                modifier = Modifier
                    .weight(8f)
                    .fillMaxWidth(),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Text(
                text = comentario.usuario,
                modifier = Modifier
                    .weight(2f)
                    .fillMaxWidth(),
                fontSize = 14.sp,

                )
        }

        Text(
            text = comentario.comentario,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,

            )
    }
}
