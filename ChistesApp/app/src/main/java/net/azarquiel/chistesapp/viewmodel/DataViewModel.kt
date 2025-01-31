package net.azarquiel.chistesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.chistesapp.api.MainRepository
import net.azarquiel.chistesapp.model.Categoria
import net.azarquiel.chistesapp.model.Chiste

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

    fun saveChiste( chiste: Chiste):MutableLiveData<Chiste> {
        val chisteResponse= MutableLiveData<Chiste>()
        GlobalScope.launch(Main) {
            chisteResponse.value = repository.saveChiste(chiste)
        }
        return chisteResponse
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
