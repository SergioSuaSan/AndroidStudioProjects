package net.azarquiel.playasandaluciajpa.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import net.azarquiel.playasandaluciajpa.model.Costa
import net.azarquiel.playasandaluciajpa.model.PlayaAndaluDB

class CostaRepository(application: Application) {
    val daoCosta = PlayaAndaluDB.getDatabase(application).daoCosta()
    // select
    fun getAllCostas(): LiveData<List<Costa>> {
        return daoCosta.getAllCostas()
    }
    fun getCostaDetail(id:Int): LiveData<Costa> {
        return daoCosta.getCostaDetail(id)
    }
}
