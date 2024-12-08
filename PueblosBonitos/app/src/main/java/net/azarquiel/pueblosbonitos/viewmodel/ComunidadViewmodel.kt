package net.azarquiel.pueblosbonitos.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import net.azarquiel.pueblosbonitos.model.Comunidad
import net.azarquiel.pueblosbonitos.repositories.ComunidadRepository

class ComunidadViewModel (application: Application) :
    AndroidViewModel(application) {
    private var repository: ComunidadRepository =
        ComunidadRepository(application)
    fun getAllComunidades(): LiveData<List<Comunidad>> {
        return repository.getAllComunidades()
    }
}