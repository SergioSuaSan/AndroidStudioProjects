package net.azarquiel.acbroomjpc.viewmodel

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.azarquiel.acbroomjpc.view.MainActivity
import net.azarquiel.bingoshare.util.Util



class MainViewModel(mainActivity: MainActivity) : ViewModel() {

    init {
        Util.inyecta(mainActivity, "acb.sqlite")


        val equipoViewModel = ViewModelProvider(mainActivity).get(EquipoViewModel::class.java)

        equipoViewModel.getEquipos().observe(mainActivity,
            Observer { equipos ->
            equipos.forEach {
                Log.d("Sergio", it.toString())
            }
        })

    }

}