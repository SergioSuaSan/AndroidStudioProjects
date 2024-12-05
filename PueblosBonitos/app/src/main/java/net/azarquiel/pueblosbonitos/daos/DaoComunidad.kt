package net.azarquiel.pueblosbonitos.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Update
import net.azarquiel.pueblosbonitos.model.Comunidad

@Dao
interface DaoComunidad {
    @Query("SELECT * from Coºmunidad ORDER BY nombre ASC")
    fun getAllProducts(): LiveData<List<Comunidad>>

    @Query("SELECT * from Comunidad where id = :paramid")
    fun getProductById(paramid:Int): LiveData<Comunidad>

    @Insert
    fun insert(Comunidad: Comunidad)

    @Query("DELETE FROM Comunidad WHERE id=:id")
    fun delete(id:Int)

    @Update
    fun update(Comunidad: Comunidad)
}
