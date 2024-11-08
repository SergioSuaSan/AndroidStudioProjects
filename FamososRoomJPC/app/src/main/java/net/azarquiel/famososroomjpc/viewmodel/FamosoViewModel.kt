package net.azarquiel.famososroomjpc.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import net.azarquiel.famososroomjpc.model.Famoso
import net.azarquiel.famososroomjpc.model.FamosoRepository


class FamosoViewModel  (application: Application) : AndroidViewModel(application) {

    private var repository: FamosoRepository = FamosoRepository(application)

    fun getFamosos(): LiveData<List<Famoso>> {
        return repository.getFamosos()
    }

    fun getFamososByCategoria(categoriaId: Int): LiveData<List<Famoso>> {
        return repository.getFamososByCategoria(categoriaId)
    }
}
