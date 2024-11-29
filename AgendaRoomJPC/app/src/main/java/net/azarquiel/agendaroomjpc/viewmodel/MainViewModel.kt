package net.azarquiel.agendaroomjpc.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.azarquiel.agendaroomjpc.model.Amigo

class MainViewModel : ViewModel(){


    val amigos = MutableLiveData<List<Amigo>>()
    val openDialog = MutableLiveData<Boolean>()

    fun setDialog(b: Boolean) {

    }



}
