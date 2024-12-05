package net.azarquiel.pueblosbonitos.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import net.azarquiel.pueblosbonitos.model.Provinciawp
import net.azarquiel.pueblosbonitos.model.Pueblowp

class daos {


    @Dao
    interface DaoProvincia {
        @Transaction
        @Query("SELECT * FROM Provincia")
        fun getProvinciaswp(): LiveData<List<Provinciawp>>
    }
    @Dao
    interface DaoPueblo {
        @Transaction
        @Query("SELECT * FROM pueblo where id=:idprovincia")
        fun getEstablecimientoWithTapas(idprovincia:Int): LiveData<Pueblowp>
    }

}