package net.azarquiel.agendaroomjpc.model


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Update

@Dao
interface AmigoDao {
    @Query("SELECT * from amigo ORDER BY nombre ASC")
    fun getAllAmigos(): LiveData<List<Amigo>>

    @Query("SELECT * from amigo where id = :paramid")
    fun getAmigosById(paramid:Int): LiveData<Amigo>

    @Insert
    fun insert(amigo: Amigo)

    @Query("DELETE FROM amigo WHERE id=:id")
    fun delete(id:Int)

    @Update
    fun update(amigo: Amigo)
}
