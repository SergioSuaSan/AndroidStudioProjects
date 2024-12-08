package net.azarquiel.pueblosbonitos.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.pueblosbonitos.model.Pueblo
import net.azarquiel.pueblosbonitos.model.Pueblowp
import net.azarquiel.pueblosbonitos.repositories.PuebloRepository

class PuebloViewModel (application: Application) :
    AndroidViewModel(application) {
    private var repository: PuebloRepository =
        PuebloRepository(application)
    fun getPueblowpByComunidad(comunidad:Int): LiveData<List<Pueblowp>> {
        return repository.getPueblowpByComunidad(comunidad)
    }
    fun update(pueblo: Pueblo) {
        GlobalScope.launch() {
            repository.update(pueblo)
            launch(Dispatchers.Main) {
            }
        }
    }
    fun getPueblowpDetail(id: Int): LiveData<Pueblowp> {
        return repository.getPueblowpDetail(id)
    }

    fun getPueblowpFavByComunidad(nombre: String): LiveData<List<Pueblowp>>  {
        return repository.getPueblowpFavByComunidad(nombre)
    }
}