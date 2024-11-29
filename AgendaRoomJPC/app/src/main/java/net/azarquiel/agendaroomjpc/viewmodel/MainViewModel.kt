package net.azarquiel.agendaroomjpc.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.azarquiel.agendaroomjpc.MainActivity
import net.azarquiel.agendaroomjpc.model.Amigo

class MainViewModel(mainActivity: MainActivity) : ViewModel(){


    val openDialog = MutableLiveData<Boolean>()
    private val _amigos: MutableLiveData<List<Amigo>> = MutableLiveData()
    val amigos: LiveData<List<Amigo>> = _amigos
    init {


        val amigoViewModel = ViewModelProvider(mainActivity).get(AmigoViewModel::class.java)

        amigoViewModel.getAllProdutcs().observe(mainActivity, Observer { amigos ->
            _amigos.value = amigos
        })
    }


    fun setDialog(b: Boolean) {
        openDialog.value = b

    }
    fun addAmigo(amigo: Amigo) {
        //amigoViewModel.insert(amigo)

    }



}
