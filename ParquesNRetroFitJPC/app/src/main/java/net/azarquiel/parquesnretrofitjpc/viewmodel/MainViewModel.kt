package net.azarquiel.parquesnretrofitjpc.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.azarquiel.parquesnretrofitjpc.api.MainRepository
import net.azarquiel.parquesnretrofitjpc.model.Comunidad
import net.azarquiel.parquesnretrofitjpc.model.Imagen
import net.azarquiel.parquesnretrofitjpc.model.Parques
import net.azarquiel.parquesnretrofitjpc.view.MainActivity

class MainViewModel(mainActivity: MainActivity) : ViewModel() {


    val mainActivity by lazy { mainActivity }
    val repository = MainRepository()
    private val _comunidades = MutableLiveData<List<Comunidad>>()
    val comunidades: LiveData<List<Comunidad>> = _comunidades
    private val _parques = MutableLiveData<List<Parques>>()
    val parques: LiveData<List<Parques>> = _parques
    private val _imagenes = MutableLiveData<List<Imagen>>()
    val imagenes: LiveData<List<Imagen>> = _imagenes
    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg
    private val _likes = MutableLiveData<Int>()
    val likes: LiveData<Int> = _likes


    init {
        val viewModel = ViewModelProvider(mainActivity)[DataViewModel::class.java]
        viewModel.getComunidades().observe(mainActivity) {
            it?.let {
                _comunidades.value = it
            }
        }



    }


    fun getParquesByComunidad(id: Int) {
        val viewModel = ViewModelProvider(mainActivity)[DataViewModel::class.java]
        viewModel.getParquesByComunidad(id).observe(mainActivity) {
            it?.let {
                _parques.value = it
            }
        }

    }

    fun getImagenesByParque(id: Int) {
        val viewModel = ViewModelProvider(mainActivity)[DataViewModel::class.java]
        viewModel.getImagenesByParque(id).observe(mainActivity) {
            it?.let {
                _imagenes.value = it
            }
        }

    }


    fun sumaFav(parque: Parques) {

        parque.likes++
        _likes.value = parque.likes
        val viewModel = ViewModelProvider(mainActivity)[DataViewModel::class.java]
        viewModel.darLike(parque.id).observe(mainActivity) {
            it?.let {
                _msg.value = it
            }

        }

    }


}
