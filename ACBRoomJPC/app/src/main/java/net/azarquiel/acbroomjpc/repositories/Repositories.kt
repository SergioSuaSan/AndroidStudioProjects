package net.azarquiel.acbroomjpc.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import net.azarquiel.acbroomjpc.model.AcbDB
import net.azarquiel.acbroomjpc.model.Equipo
import net.azarquiel.acbroomjpc.model.EquipoConJugadores
import net.azarquiel.acbroomjpc.model.Jugador


class EquipoRepository(application: Application) {


    val daoEquipo = AcbDB.getDatabase(application).daoEquipo()
    // select
    fun getEquipos(): LiveData<List<EquipoConJugadores>> {
        return daoEquipo.getEquipos()
    }
}



class JugadorRepository(application: Application) {

    val daoJugador = AcbDB.getDatabase(application).daoJugador()

    // update
    fun update(jugador: Jugador) {
        daoJugador.update(jugador)
    }
}





