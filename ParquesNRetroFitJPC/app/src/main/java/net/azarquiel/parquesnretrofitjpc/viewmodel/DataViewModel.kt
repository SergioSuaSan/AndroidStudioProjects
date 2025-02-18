package net.azarquiel.parquesnretrofitjpc.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.parquesnretrofitjpc.api.MainRepository
import net.azarquiel.parquesnretrofitjpc.model.Comunidad
import net.azarquiel.parquesnretrofitjpc.model.Imagen
import net.azarquiel.parquesnretrofitjpc.model.Parques

// ……

/**
 * Created by Paco Pulido.
 */
class DataViewModel : ViewModel() {

    private var repository: MainRepository = MainRepository()


    //FUNCIONES GET
    fun getComunidades(): MutableLiveData<List<Comunidad>> {
        val comunidades = MutableLiveData<List<Comunidad>>()
        GlobalScope.launch(Main) {
            comunidades.value = repository.getComunidades()
        }
        return comunidades
    }
    fun getParques(): MutableLiveData<List<Parques>> {
        val parques = MutableLiveData<List<Parques>>()
        GlobalScope.launch(Main) {
            parques.value = repository.getParques()
        }
        return parques
    }
    fun getParquesByComunidad(idcomunidad: Int): MutableLiveData<List<Parques>> {
        val parques = MutableLiveData<List<Parques>>()
        GlobalScope.launch(Main) {
            parques.value = repository.getParquesByComunidad(idcomunidad)
        }
        return parques
    }
    fun getImagenesByParque(idparque: Int): MutableLiveData<List<Imagen>> {
        val imagenes = MutableLiveData<List<Imagen>>()
        GlobalScope.launch(Main) {
            imagenes.value = repository.getImagenesByParque(idparque)
        }
        return imagenes
    }

    //FUNCIONES POST
    fun darLike(idparque: Int): MutableLiveData<String> {
        val msg = MutableLiveData<String>()
        GlobalScope.launch(Main) {
            msg.value = repository.darLike(idparque)
        }
        return msg
    }




// ……..   resto de métodos del ViewModel ………..
}

