package net.azarquiel.famososroomjpc.model

import android.app.Application
import androidx.lifecycle.LiveData
import net.azarquiel.famososroomjpc.model.Famoso
import net.azarquiel.famososroomjpc.model.FamososDB

class FamosoRepository(application: Application) {
    val famosoDAO = FamososDB.getDatabase(application).famosoDAO()

    fun getFamosos(): LiveData<List<Famoso>> {
        return famosoDAO.getFamosos()
    }

    fun getFamososByCategoria(categoriaId: Int): LiveData<List<Famoso>> {
        return famosoDAO.getFamososByCategoria(categoriaId)
    }
}
