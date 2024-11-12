package net.azarquiel.a2pantallas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    fun changeName(s: String) {
        _name.value = s

    }
}