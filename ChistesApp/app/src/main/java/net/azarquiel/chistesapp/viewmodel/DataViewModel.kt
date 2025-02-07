package net.azarquiel.chistesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.chistesapp.api.MainRepository
import net.azarquiel.chistesapp.model.Categoria
import net.azarquiel.chistesapp.model.Chiste
import net.azarquiel.chistesapp.model.Punto
import net.azarquiel.chistesapp.model.Usuario

// ……

/**
 * Created by Paco Pulido.
 */
class DataViewModel : ViewModel() {

    private var repository: MainRepository = MainRepository()

    fun getCategorias(): MutableLiveData<List<Categoria>> {
        val categorias = MutableLiveData<List<Categoria>>()
        GlobalScope.launch(Main) {
            categorias.value = repository.getCategorias()
        }
        return categorias
    }
    fun getChistesPorCategoria(idcategoria : String): MutableLiveData<List<Chiste>> {
        val chistes = MutableLiveData<List<Chiste>>()
        GlobalScope.launch(Main) {
            chistes.value = repository.getChistesPorCategoria(idcategoria)
        }
        return chistes
    }
    fun getMedia(idchiste : String): MutableLiveData<String> {
        val avg = MutableLiveData<String>()
        GlobalScope.launch(Main) {
            avg.value = repository.getMedia(idchiste)
        }
        return avg
    }
    fun getDataUsuarioPorNickPass(nick:String, pass: String): MutableLiveData<Usuario> {
        val usuario = MutableLiveData<Usuario>()
        GlobalScope.launch(Main) {
            usuario.value = repository.getDataUsuarioPorNickPass(nick, pass)
        }
        return usuario
    }

    fun saveChiste( chiste: Chiste):MutableLiveData<Chiste> {
        val chisteResponse= MutableLiveData<Chiste>()
        GlobalScope.launch(Main) {
            chisteResponse.value = repository.saveChiste(chiste)
        }
        return chisteResponse
    }
    fun saveUsuario(usuario: Usuario):MutableLiveData<Usuario> {
        val usuarioResponse= MutableLiveData<Usuario>()
        GlobalScope.launch(Main) {
            usuarioResponse.value = repository.saveUsuario(usuario)
        }
        return usuarioResponse
    }
    fun savePuntos(idChiste:String, punto: Punto):MutableLiveData<Punto> {
        val puntoResponse= MutableLiveData<Punto>()
        GlobalScope.launch(Main) {
            puntoResponse.value = repository.savePuntos(idChiste, punto)
        }
        return puntoResponse
    }


// ……..   resto de métodos del ViewModel ………..
}

/*
Otro ejemplo:

fun saveComentario(idmovie:String, comentario: Comentario):MutableLiveData<Comentario> {
    val comentarioResponse= MutableLiveData<Comentario>()
    GlobalScope.launch(Main) {
        comentarioResponse.value = repository.saveComentario(idmovie, comentario)
    }
    return comentarioResponse
}
*/
