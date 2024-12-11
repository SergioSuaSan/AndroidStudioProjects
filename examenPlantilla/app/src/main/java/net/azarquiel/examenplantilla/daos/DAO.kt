package net.azarquiel.examenplantilla.daos
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update

@Dao
interface DAO
{
//    @Update
//    fun update(pueblo: Pueblo)
//
//    @Query("select * from pueblo where provincia in (select id from provincia where comunidad = :comunidadid)")
//    fun getPueblosByComunidad(comunidadid: Int):LiveData<List<PuebloWithProvincia>>
//
//    @Query("select * from pueblo where fav = 1 and provincia in (select id from provincia where comunidad = :comunidadid)")
//    fun getPueblosFavByComunidad(comunidadid: Int):LiveData<List<PuebloWithProvincia>>
}

