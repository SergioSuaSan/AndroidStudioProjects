package net.azarquiel.pueblosbonitos.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import net.azarquiel.pueblosbonitos.model.Comunidad
import net.azarquiel.pueblosbonitos.model.PueblosBonitosDB

class ComunidadRepository(application: Application) {
    val daoComunidad = PueblosBonitosDB.getDatabase(application).daoComunidad()
    // select
    fun getAllComunidades(): LiveData<List<Comunidad>> {
        return daoComunidad.getAllComunidades()
    }
}