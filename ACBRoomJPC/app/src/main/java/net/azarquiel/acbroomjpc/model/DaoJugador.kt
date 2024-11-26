package net.azarquiel.acbroomjpc.model

import androidx.room.Dao
import androidx.room.Update

@Dao
interface DaoJugador {
    @Update
    fun update(producto: Jugador)
}

