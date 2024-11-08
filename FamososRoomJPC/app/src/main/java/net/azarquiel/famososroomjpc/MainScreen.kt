package net.azarquiel.famososroomjpc

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.azarquiel.famososroomjpc.model.Famoso


@Composable
fun MainScreen(viewModel: MainViewModel) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Scaffold(
        topBar = { CustomTopBar(textState) },
        content = { padding ->
            CustomContent(padding, viewModel, textState)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(textState: MutableState<TextFieldValue>) {
    TopAppBar(
        title = { Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Famosos",
                Modifier.padding(0.dp,0.dp,10.dp,0.dp))
            SearchView(textState)
        }
        },
        colors = topAppBarColors(
            containerColor = colorResource(R.color.azuloo),
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}


@Composable
fun CustomContent(
    padding: PaddingValues,
    viewModel: MainViewModel,
    textState: MutableState<TextFieldValue>
) {
    val famosos = viewModel.famosos.observeAsState(listOf())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),)
    {

       
        LazyColumn {
            itemsIndexed(famosos.value.filter { it.p_name_es.contains(textState.value.text, ignoreCase = true) })
            { i, famoso ->
                if (i==0){
                    Header()
                }
                CardLinea(famoso)
            }
        }
    }
}
@Composable
fun Header() {
    Image(
        painterResource(R.drawable.header),
        contentDescription = "header"
    )
}

@Composable
fun CardLinea(famoso: Famoso) {
    val context = LocalContext.current
    var id = context.resources.getIdentifier("p${famoso.p_id}", "drawable", context.packageName)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.azulo),
            contentColor = colorResource(R.color.azulc)),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Image(
                painter = painterResource(id),
                contentDescription = famoso.p_name_es,
                modifier = Modifier.weight(3f).size(100.dp,150.dp).padding(8.dp)
            )
            Text(
                text = famoso.p_name_es,
                modifier = Modifier.weight(8f).fillMaxWidth(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
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
            .fillMaxWidth()
            .padding(2.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(2.dp, Color.DarkGray, RoundedCornerShape(20.dp)),
        placeholder = {
            Text(text = "Search here...")
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = colorResource(R.color.azulo),
            focusedPlaceholderColor = colorResource(R.color.azulo),
            unfocusedPlaceholderColor = colorResource(R.color.azulc),
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CardLinea(Famoso(1,1,"Famoso1"))
}

