package net.azarquiel.parquesnretrofitjpc.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.azarquiel.parquesnretrofitjpc.model.Comunidad
import net.azarquiel.parquesnretrofitjpc.model.Imagen
import net.azarquiel.parquesnretrofitjpc.model.Parques
import net.azarquiel.parquesnretrofitjpc.view.MainActivity

class MainViewModel(mainActivity: MainActivity) : ViewModel() {

    var viewModel = ViewModelProvider(mainActivity)[DataViewModel::class.java]
    val mainActivity by lazy { mainActivity }
    private val _comunidades = MutableLiveData<List<Comunidad>>()
    val comunidades: LiveData<List<Comunidad>> = _comunidades
    private val _parques = MutableLiveData<List<Parques>>()
    val parques: LiveData<List<Parques>> = _parques
    private val _imagenes = MutableLiveData<List<Imagen>>()
    val imagenes: LiveData<List<Imagen>> = _imagenes
    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg


    init {
         viewModel = ViewModelProvider(mainActivity)[DataViewModel::class.java]
        viewModel.getComunidades().observe(mainActivity) {
            it?.let {
                _comunidades.value = it
                val todas = ArrayList(it)
                todas.add(0, Comunidad(0, "España"))
                _comunidades.value = todas
            }
        }



    }


    fun getParquesByComunidad(id: Int) {
        if (id == 0) {
            viewModel.getParques().observe(mainActivity) {
                it?.let {
                    _parques.value = it
                }
            }
        } else {
            viewModel.getParquesByComunidad(id).observe(mainActivity) {
                it?.let {
                    _parques.value = it
                }
            }
        }

    }

    fun getImagenesByParque(id: Int) {
        viewModel = ViewModelProvider(mainActivity)[DataViewModel::class.java]
        viewModel.getImagenesByParque(id).observe(mainActivity) {
            it?.let {
                _imagenes.value = it
            }
        }

    }


    fun sumaFav(parque: Parques) {
        parque.likes++
        Log.d("paco", "sumaFav: ${parque.likes}")
        viewModel.darLike(parque.id).observe(mainActivity) {
            it?.let {
                _msg.value = it
                Toast.makeText(mainActivity, "Gracias por tu like", Toast.LENGTH_SHORT).show()
            }

            Log.d("paco", "sumaFavFinal: ${parque.likes}")
        }

    }


}
