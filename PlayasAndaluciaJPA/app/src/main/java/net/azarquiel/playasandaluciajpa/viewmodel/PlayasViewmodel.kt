package net.azarquiel.playasandaluciajpa.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.playasandaluciajpa.model.Playa
import net.azarquiel.playasandaluciajpa.model.PlayawCosta
import net.azarquiel.playasandaluciajpa.repositories.PlayaRepository

class PlayasViewModel (application: Application) :
    AndroidViewModel(application) {
    private var repository: PlayaRepository =
        PlayaRepository(application)
    fun getPlayasbyCosta(id:Int): LiveData<List<PlayawCosta>> {
        return repository.getPlayabyCosta(id)
    }
    fun update(playa: Playa) {
        GlobalScope.launch() {
            repository.update(playa)
            launch(Dispatchers.Main) {
            }
        }
    }
    fun getPlayaDetail(id:Int): LiveData<PlayawCosta> {
        return repository.getPlayaDetail(id)
    }
    fun getFavPlayasbyCosta(id:Int): LiveData<List<PlayawCosta>> {
        return repository.getPlayaFavbyCosta(id)
    }




}