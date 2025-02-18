package net.azarquiel.chistesretrofitjpc.screens

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
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import net.azarquiel.chistesretrofitjpc.R
import net.azarquiel.chistesretrofitjpc.model.Categoria
import net.azarquiel.chistesretrofitjpc.viewmodel.MainViewModel


@Composable
fun CategoriasScreen(navController: NavHostController, viewModel: MainViewModel) {
    Scaffold(
        topBar = { MasterTopBar() },
        content = { padding ->
            MasterContent(padding, navController, viewModel)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasterTopBar() {
    TopAppBar(
        title = { Text(text = "Categorias") },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}


@Composable
fun MasterContent(
    padding: PaddingValues,
    navController: NavHostController,
    viewModel: MainViewModel
) {
    val categorias = viewModel.categorias.observeAsState(listOf())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
    )
    {
        LazyColumn {
            itemsIndexed(categorias.value) { i, categoria ->
                if (i==0) {
                    Image(
                        painter = painterResource(id = R.drawable.header),
                        contentDescription = "Chistes",
                    )
                }
                CardLinea(categoria, navController)
            }
        }
    }
}

@Composable
fun CardLinea(categoria: Categoria, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                navController.currentBackStackEntry?.savedStateHandle?.set("categoria", categoria)
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
            AsyncImage(
                model = "http://www.ies-azarquiel.es/paco/apichistes/img/${categoria.id}.png",
                contentDescription = "Description  Image",
                modifier = Modifier.size(130.dp, 80.dp)
            )

            Text(
                text = categoria.nombre,
                modifier = Modifier.weight(8f).fillMaxWidth(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

