package net.azarquiel.famososroomjpc.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import net.azarquiel.famososroomjpc.model.Famoso


@Dao
interface FamosoDAO {
    @Query("SELECT * from famoso ORDER BY p_name_es")
    fun getFamosos(): LiveData<List<Famoso>>
    @Query("SELECT * from famoso where c_id = :categoriaId ORDER BY p_name_es")
    fun getFamososByCategoria(categoriaId: Int): LiveData<List<Famoso>>
}
