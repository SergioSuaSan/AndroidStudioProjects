package net.azarquiel.playasandaluciajpa.repositories


import android.app.Application
import androidx.lifecycle.LiveData
import net.azarquiel.playasandaluciajpa.model.Costa
import net.azarquiel.playasandaluciajpa.model.Playa
import net.azarquiel.playasandaluciajpa.model.PlayaAndaluDB
import net.azarquiel.playasandaluciajpa.model.PlayawCosta

class PlayaRepository(application: Application) {
    val daoPlaya = PlayaAndaluDB.getDatabase(application).daoPlaya()
    // select
    fun getPlayabyCosta(idCosta:Int): LiveData<List<PlayawCosta>> {
        return daoPlaya.getPlayabyCosta(idCosta)
    }
    fun getPlayaDetail(id:Int): LiveData<PlayawCosta> {
        return daoPlaya.getPlayaDetail(id)
    }
    fun getPlayaFavbyCosta(idCosta: Int): LiveData<List<PlayawCosta>> {
        return daoPlaya.getPlayaFavbyCosta(idCosta)
    }
    fun update(playa: Playa) {
        daoPlaya.update(playa)
    }
}
