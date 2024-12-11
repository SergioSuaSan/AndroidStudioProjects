package net.azarquiel.playasandaluciajpa.daos


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import net.azarquiel.playasandaluciajpa.model.Costa
import net.azarquiel.playasandaluciajpa.model.Playa
import net.azarquiel.playasandaluciajpa.model.PlayawCosta


@Dao
interface DaoPlaya {


    @Transaction
    @Query("SELECT * FROM playa where  costa = :idCosta")
    fun getPlayabyCosta(idCosta:Int): LiveData<List<PlayawCosta>>

    @Transaction
    @Query("SELECT * FROM playa where  id =:id")
    fun getPlayaDetail(id:Int): LiveData<PlayawCosta>

    @Transaction
    @Query("SELECT * FROM playa where  azul = 1 and costa = :idCosta ")
    fun getPlayaFavbyCosta(idCosta: Int): LiveData<List<PlayawCosta>>

    @Update
    fun update(playa: Playa)
}

@Dao
interface DaoCosta {
    @Query("SELECT * from Costa ORDER BY nombre ASC")
    fun getAllCostas(): LiveData<List<Costa>>

    @Query("SELECT * from Costa where id = :id")
    fun getCostaDetail(id:Int): LiveData<Costa>

}
