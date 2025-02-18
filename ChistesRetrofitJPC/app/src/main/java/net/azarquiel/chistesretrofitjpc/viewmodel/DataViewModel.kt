package net.azarquiel.chistesretrofitjpc.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.chistesretrofitjpc.api.MainRepository
import net.azarquiel.chistesretrofitjpc.model.Categoria
import net.azarquiel.chistesretrofitjpc.model.Chiste
import net.azarquiel.chistesretrofitjpc.model.Punto
import net.azarquiel.chistesretrofitjpc.model.Usuario

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
    fun getChistesByCategoria(idcategoria:Int): MutableLiveData<List<Chiste>> {
        val chistes = MutableLiveData<List<Chiste>>()
        GlobalScope.launch(Main) {
            chistes.value = repository.getChistesByCategoria(idcategoria)
        }
        return chistes
    }
    fun getAvg(idchiste:Int): MutableLiveData<String> {
        val avg = MutableLiveData<String>()
        GlobalScope.launch(Main) {
            avg.value = repository.getAvg(idchiste)
        }
        return avg
    }
    fun getDataUsuarioPorNickPass(nick:String, pass:String): MutableLiveData<Usuario> {
        val usuario = MutableLiveData<Usuario>()
        GlobalScope.launch(Main) {
            usuario.value = repository.getDataUsuarioPorNickPass(nick, pass)
        }
        return usuario
    }

    fun saveBar(usuario: Usuario):MutableLiveData<Usuario> {
        val usurioResponse= MutableLiveData<Usuario>()
        GlobalScope.launch(Main) {
            usurioResponse.value = repository.saveUsuario(usuario)
        }
        return usurioResponse
    }
    fun saveChiste(chiste: Chiste):MutableLiveData<Chiste> {
        val chisteResponse= MutableLiveData<Chiste>()
        GlobalScope.launch(Main) {
            chisteResponse.value = repository.saveChiste(chiste)
        }
        return chisteResponse
    }
    fun savePunto(punto: Punto):MutableLiveData<Punto> {
        val puntoResponse= MutableLiveData<Punto>()
        GlobalScope.launch(Main) {
            puntoResponse.value = repository.savePunto(punto)
        }
        return puntoResponse
    }
}
