package net.azarquiel.metroroomjpc.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import net.azarquiel.metroroomjpc.model.Linea
import net.azarquiel.metroroomjpc.model.LineaWithEstaciones


@Dao
interface LineaDAO {
    @Transaction
    @Query("SELECT * from linea ORDER BY id ASC")
    fun getAllLineas(): LiveData<List<LineaWithEstaciones>>
}
