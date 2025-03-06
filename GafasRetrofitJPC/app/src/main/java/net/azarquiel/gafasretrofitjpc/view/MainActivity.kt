package net.azarquiel.gafasretrofitjpc.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import net.azarquiel.gafasretrofitjpc.model.Usuario
import net.azarquiel.gafasretrofitjpc.navegation.AppNavigation
import net.azarquiel.gafasretrofitjpc.ui.theme.GafasRetrofitJPCTheme
import net.azarquiel.gafasretrofitjpc.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    // share preferences
    private lateinit var usuariosSH: SharedPreferences
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // share preferences
        usuariosSH = getSharedPreferences("usuario", Context.MODE_PRIVATE)
        // share viewModel aquí. Fuera de los composables
        val viewModel = MainViewModel(this)
        // Cargar usuario guardado
        val usuarioGuardado = getUsuario()
        if (usuarioGuardado != null) {
            Log.d("TAG", "Usuario cargado: $usuarioGuardado")
            viewModel.setUsuario(usuarioGuardado)
        }
        Log.d("TAG", "onCreate: ${viewModel.usuario.value}")
        setContent {
            GafasRetrofitJPCTheme  {
                AppNavigation(viewModel)
            }
        }
    }
    // Introducir Objeto en un SharePrefereces a través de un editor
    fun saveUsuario(usuario: Usuario) {
        val jsonUsuario = Gson().toJson(usuario)
        val editor = usuariosSH.edit()
        editor.putString("usuario", jsonUsuario)
        editor.apply()
    }

    // Buscar el Objeto en ese Sharepreference a través de su Key
    // desde el propio SharePrefertences
    private fun getUsuario(): Usuario? {
        val jsonUsuario = usuariosSH.getString("usuario", null)
        return if (jsonUsuario != null) Gson().fromJson(jsonUsuario, Usuario::class.java) else null
    }
}
