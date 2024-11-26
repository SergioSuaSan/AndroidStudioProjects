package net.azarquiel.acbroomjpc.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.acbroomjpc.model.Jugador
import net.azarquiel.acbroomjpc.repositories.JugadorRepository

class JugadorViewmodel (application: Application) : AndroidViewModel(application) {

    private var repository: JugadorRepository = JugadorRepository(application)



    @OptIn(DelicateCoroutinesApi::class)
    fun update(jugador: Jugador) {
        GlobalScope.launch() {
            repository.update(jugador)
            launch(Dispatchers.Main) {
            }
        }
    }
}
