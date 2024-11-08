package net.azarquiel.famososroomjpc.model

import android.app.Application
import androidx.lifecycle.LiveData


class CategoriaRepository(application: Application) {
    val categoriaDAO = FamososDB.getDatabase(application).categoriaDAO()
    // select
    fun getCategorias(): LiveData<List<Categoria>> {
        return categoriaDAO.getCategorias()
    }
}
