package net.azarquiel.pueblosbonitos.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import net.azarquiel.pueblosbonitos.model.Comunidad
import net.azarquiel.pueblosbonitos.model.Pueblo
import net.azarquiel.pueblosbonitos.model.Pueblowp


@Dao
interface DaoPueblo {
//    @Transaction
//    @Query("SELECT * FROM pueblo where id=:idprovincia")
//    fun getPueblowpByProvincia(idprovincia:Int): LiveData<List<Pueblowp>>

    @Transaction
    @Query("SELECT * FROM pueblo where  provincia in (select id from Provincia where comunidad = :idComunidad)")
    fun getPueblowpByComunidad(idComunidad:Int): LiveData<List<Pueblowp>>

    @Transaction
    @Query("SELECT * FROM pueblo where  id =:id")
    fun getPueblowpDetail(id:Int): LiveData<Pueblowp>

    @Transaction
    @Query("SELECT * FROM pueblo where  fav = 1 and provincia in (select id from Provincia where comunidad = (select id from Comunidad where nombre = :nombre))")
    fun getPueblowpFavByComunidad(nombre: String): LiveData<List<Pueblowp>>

    @Update
    fun update(pueblo: Pueblo)
}

@Dao
interface DaoComunidad {
    @Query("SELECT * from Comunidad ORDER BY nombre ASC")
    fun getAllComunidades(): LiveData<List<Comunidad>>

}
