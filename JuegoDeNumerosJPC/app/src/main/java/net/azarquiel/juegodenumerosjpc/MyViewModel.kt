package net.azarquiel.juegodenumerosjpc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    private val _numeroAleatorio = MutableLiveData<Int>()
    val numeroAleatorio: LiveData<Int> = _numeroAleatorio
}