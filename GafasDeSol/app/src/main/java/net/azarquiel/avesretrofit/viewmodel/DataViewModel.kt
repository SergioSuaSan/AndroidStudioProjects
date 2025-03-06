package net.azarquiel.avesretrofit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.avesretrofit.api.MainRepository
import net.azarquiel.avesretrofit.model.Comentario
import net.azarquiel.avesretrofit.model.Recurso
import net.azarquiel.avesretrofit.model.Usuario
import net.azarquiel.avesretrofit.model.Zona

// ……

/**
 * Created by Paco Pulido.
 */
class DataViewModel : ViewModel() {

    private var repository: MainRepository = MainRepository()

    //FUNCIONES GET
    fun getDataUsuarios(): MutableLiveData<List<Usuario>> {
        val usuarios = MutableLiveData<List<Usuario>>()
        GlobalScope.launch(Main) {
            usuarios.value = repository.getDataUsuarios()
        }
        return usuarios
    }


    fun getDataZonas(): MutableLiveData<List<Zona>> {
        val zonas = MutableLiveData<List<Zona>>()
        GlobalScope.launch(Main) {
            zonas.value = repository.getDataZonas()
        }
        return zonas
    }

    fun getDataRecursos(idzona: Int): MutableLiveData<List<Recurso>> {
        val recursos = MutableLiveData<List<Recurso>>()
        GlobalScope.launch(Main) {
            recursos.value = repository.getDataRecursos(idzona)
        }
        return recursos
    }

    fun getDataUsuarioPorNickPass(nick: String, pass: String): MutableLiveData<Usuario> {
        val usuario = MutableLiveData<Usuario>()
        GlobalScope.launch(Main) {
            usuario.value = repository.getDataUsuarioPorNickPass(nick, pass)
        }
        return usuario
    }

    fun getDataComentarios(idrecurso: Int): MutableLiveData<List<Comentario>> {
        val comentarios = MutableLiveData<List<Comentario>>()
        GlobalScope.launch(Main) {
            comentarios.value = repository.getDataComentarios(idrecurso)
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

    fun saveComentario(idrecurso: Int, idUsuario: Int, fecha: String, comentario: String): MutableLiveData<Comentario> {
        val comentarioResponse = MutableLiveData<Comentario>()
        Log.d("TAG", "saveComentarioDATAVIEWMODEL: $idrecurso, $idUsuario, $fecha, $comentario")
        GlobalScope.launch(Main) {
            comentarioResponse.value = repository.saveComentario(idrecurso, idUsuario, fecha, comentario)
        }
        return comentarioResponse
    }

}

