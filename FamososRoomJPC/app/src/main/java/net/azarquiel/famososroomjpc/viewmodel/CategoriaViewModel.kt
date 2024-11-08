package net.azarquiel.famososroomjpc.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import net.azarquiel.famososroomjpc.model.Categoria
import net.azarquiel.famososroomjpc.model.CategoriaRepository


class CategoriaViewModel (application: Application) : AndroidViewModel(application) {
    private var repository: CategoriaRepository = CategoriaRepository(application)
    fun getCategorias(): LiveData<List<Categoria>> {
        return repository.getCategorias()
    }
}
