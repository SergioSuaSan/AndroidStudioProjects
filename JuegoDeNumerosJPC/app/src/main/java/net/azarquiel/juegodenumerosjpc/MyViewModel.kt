package net.azarquiel.juegodenumerosjpc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    private val _numeroAleatorio = MutableLiveData<Int>()
    val numeroAleatorio: LiveData<Int> = _numeroAleatorio


    fun generarNumeroAleatorio() {
        _numeroAleatorio.value = (1..100).random()
    }

    private val _contador = MutableLiveData<Int>()
    val contador: LiveData<Int> = _contador

    fun incrementarContador() {
        _contador.value = (_contador.value ?: 0) + 1
    }

}