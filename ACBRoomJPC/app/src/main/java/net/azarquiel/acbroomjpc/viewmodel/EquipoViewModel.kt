package net.azarquiel.acbroomjpc.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import net.azarquiel.acbroomjpc.model.EquipoConJugadores
import net.azarquiel.acbroomjpc.repositories.EquipoRepository


class EquipoViewModel (application: Application) : AndroidViewModel(application) {


    private var repository: EquipoRepository = EquipoRepository(application)


    fun getEquipos(): LiveData<List<EquipoConJugadores>> {
        return repository.getEquipos()
    }
}
