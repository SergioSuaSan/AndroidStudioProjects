package net.azarquiel.famososroomjpc.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import net.azarquiel.famososroomjpc.model.Categoria


@Dao
interface CategoriaDAO {
    @Query("SELECT * from Categoria ORDER BY c_name_es ASC")
    fun getCategorias(): LiveData<List<Categoria>>
}
