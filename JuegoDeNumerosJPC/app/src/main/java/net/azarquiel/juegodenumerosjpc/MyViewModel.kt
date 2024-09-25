package net.azarquiel.juegodenumerosjpc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    private val _numeroAleatorio = MutableLiveData<Int>()
    val numeroAleatorio: LiveData<Int> = _numeroAleatorio

    init {
        _numeroAleatorio.value = (1..100).random()
    }

    private val _intentos = MutableLiveData<Int>()
    val intentos: LiveData<Int> = _intentos

    fun incrementarIntentos() {
        _intentos.value = (_intentos.value ?: 0) + 1
    }



}