package net.azarquiel.gafasretrofitjpc.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.gafasretrofitjpc.api.MainRepository
import net.azarquiel.gafasretrofitjpc.model.Comentario
import net.azarquiel.gafasretrofitjpc.model.Gafa
import net.azarquiel.gafasretrofitjpc.model.Marca
import net.azarquiel.gafasretrofitjpc.model.Usuario

// ……

/**
 * Created by Paco Pulido.
 */
class DataViewModel : ViewModel() {

    private var repository: MainRepository = MainRepository()

    //FUNCIONES GET
    fun getUsuarios(): MutableLiveData<List<Usuario>> {
        val usuarios = MutableLiveData<List<Usuario>>()
        GlobalScope.launch(Main) {
            usuarios.value = repository.getUsuarios()
        }
        return usuarios
    }


    fun getMarcas(): MutableLiveData<List<Marca>> {
        val marcas = MutableLiveData<List<Marca>>()
        GlobalScope.launch(Main) {
            marcas.value = repository.getMarcas()
        }
        return marcas
    }

    fun getGafasbyMarca(idmarca: Int): MutableLiveData<List<Gafa>> {
        val gafa = MutableLiveData<List<Gafa>>()
        GlobalScope.launch(Main) {
            gafa.value = repository.getGafasbyMarca(idmarca)
        }
        return gafa
    }

    fun getDataUsuarioPorNickPass(nick: String, pass: String): MutableLiveData<Usuario> {
        val usuario = MutableLiveData<Usuario>()
        GlobalScope.launch(Main) {
            usuario.value = repository.getUsuarioPorNickPass(nick, pass)
        }
        return usuario
    }

    fun getDataComentarios(idgafa: Int): MutableLiveData<List<Comentario>> {
        val comentarios = MutableLiveData<List<Comentario>>()
        GlobalScope.launch(Main) {
            comentarios.value = repository.getComentarios(idgafa)
        }
        return comentarios
    }


    //FUNCIONES POST
    fun saveUsuario(usuario: Usuario): MutableLiveData<Usuario> {
        val usuarioResponse = MutableLiveData<Usuario>()
        GlobalScope.launch(Main) {
            usuarioResponse.value = repository.saveUsuario(usuario)
        }
        return usuarioResponse
    }

    fun saveComentario(idgafa: Int, idUsuario: Int, fecha: String, comentario: String): MutableLiveData<Comentario> {
        val comentarioResponse = MutableLiveData<Comentario>()
        Log.d("TAG", "saveComentarioDATAVIEWMODEL: $idgafa, $idUsuario, $fecha, $comentario")
        GlobalScope.launch(Main) {
            comentarioResponse.value = repository.saveComentario(idgafa, idUsuario, fecha, comentario)
        }
        return comentarioResponse
    }

}

