package net.azarquiel.chatdam.Screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ShoppingCart
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.azarquiel.chatdam.R
import net.azarquiel.chatdam.model.Post
import net.azarquiel.chatdam.viewmodel.MainViewModel


@Composable
fun MainScreen(viewModel: MainViewModel) {
   DialogFab(viewModel)
   Scaffold(
       topBar = { CustomTopBar() },
       floatingActionButton = { CustomFAB(viewModel) },
       content = { padding ->
           CustomContent(padding, viewModel)
       }
   )
}




@Composable
fun CustomFAB(viewModel:  MainViewModel) {
   FloatingActionButton(
       containerColor = MaterialTheme.colorScheme.primary,
       contentColor = MaterialTheme.colorScheme.background,
       onClick = {
           viewModel.setDialog(true)
       }) {
       Icon(Icons.Default.Edit, contentDescription = "Add")
   }
}


@Composable
fun DialogFab(viewModel: MainViewModel) {
   val context = LocalContext.current
   val openDialog = viewModel.openDialog.observeAsState(false)
   var msg by remember { mutableStateOf("") }
   if (openDialog.value) {
       AlertDialog(
           title = { Text(text = "Add Post") },
           text = {
               Column{
                   TextField(
                       modifier = Modifier.padding(bottom = 30.dp),
                       value = msg,
                       onValueChange = {
                           msg = it },
                       label = { Text("Mensaje") },
                       placeholder = { Text("Mensaje") },
                       leadingIcon = { Icon(Icons.Default.Edit, contentDescription = "Producto") },
                       keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                       singleLine = false
                   )
               } 
           },
           onDismissRequest = {  // Si pulsamos fuera cierra
               viewModel.setDialog(false)
           },
           confirmButton = {
               Button(
                   onClick = {
                       if (msg.isEmpty()) {
                           Toast.makeText( context, "required fields", Toast.LENGTH_LONG).show()
                       }
                       else {
                           viewModel.addPost(Post("Sergio", msg))
                           viewModel.setDialog(false)
                       }})
               { Text("Ok") }
           },
           dismissButton = {
               Button(
                   onClick = { viewModel.setDialog(false) })
               { Text("Cancel") }
           }
       )
   }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar() {
   TopAppBar(
       title = { Text(text = "ChatDam") },
       colors = topAppBarColors(
           containerColor = MaterialTheme.colorScheme.primary,
           titleContentColor = MaterialTheme.colorScheme.background
       )
   )
}


@Composable
fun CustomContent(padding: PaddingValues, viewModel: MainViewModel) {
    val mensajes = viewModel.mensajes.observeAsState(listOf())
   Column(
       modifier = Modifier
           .fillMaxSize()
           .padding(padding),
   )
   {
      LazyColumn {
          items(mensajes.value){mensaje ->
              CardMensaje(mensaje)
          }
      }
   }
    
}



@Composable
fun CardMensaje(post: Post) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.azulo))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
        ) {
            Text(
                text = post.user,
                color = colorResource(R.color.azulc),
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.End),
            )
            Text(
                text = post.msg,
                color = colorResource(R.color.azulc),
                fontSize = 22.sp,
            )
        }
    }
}


