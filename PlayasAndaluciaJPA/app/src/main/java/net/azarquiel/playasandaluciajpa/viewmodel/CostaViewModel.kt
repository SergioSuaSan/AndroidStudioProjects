package net.azarquiel.playasandaluciajpa.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import net.azarquiel.playasandaluciajpa.model.Costa
import net.azarquiel.playasandaluciajpa.repositories.CostaRepository

class CostaViewModel (application: Application) :
    AndroidViewModel(application) {
    private var repository: CostaRepository =
        CostaRepository(application)
    fun getAllCostas(): LiveData<List<Costa>> {
        return repository.getAllCostas()
    }
    fun getCostaDetail(id:Int): LiveData<Costa> {
        return repository.getCostaDetail(id)
    }
}