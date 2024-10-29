package net.azarquiel.famosojpc

import android.media.Image

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class MainViewModel(mainActivity: MainActivity) {


    private val _imagenes = MutableLiveData<ArrayList<Image>>()
    val imagenes: LiveData<ArrayList<Image>> = _imagenes

    val _puntos = MutableLiveData<Int>()
    val puntos: LiveData<Int> = _puntos
    private val _intentos = MutableLiveData<Int>()
    val intentos: LiveData<Int> = _intentos

    private val _famososArray = MutableLiveData<List<Famosos>>()
    val famososArray: LiveData<List<Famosos>> = _famososArray

    init {

        newGame()
    }

    private fun newGame() {
        for (i in 1..10) {


        }
    }

}
