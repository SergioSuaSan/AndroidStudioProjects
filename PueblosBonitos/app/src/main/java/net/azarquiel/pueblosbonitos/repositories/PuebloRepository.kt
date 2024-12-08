package net.azarquiel.pueblosbonitos.repositories



import android.app.Application
import androidx.lifecycle.LiveData
import net.azarquiel.pueblosbonitos.model.Pueblo
import net.azarquiel.pueblosbonitos.model.PueblosBonitosDB
import net.azarquiel.pueblosbonitos.model.Pueblowp

class PuebloRepository(application: Application) {
    val DaoPueblo = PueblosBonitosDB.getDatabase(application).daoPueblo()
    // select
    fun getPueblowpByComunidad(idComunidad:Int): LiveData<List<Pueblowp>> {
        return DaoPueblo.getPueblowpByComunidad(idComunidad)
    }
    fun update(pueblo: Pueblo) {
        DaoPueblo.update(pueblo)
    }
    fun getPueblowpDetail(id:Int): LiveData<Pueblowp> {
        return DaoPueblo.getPueblowpDetail(id)
    }

    fun getPueblowpFavByComunidad(nombre: String): LiveData<List<Pueblowp>> {
        return DaoPueblo.getPueblowpFavByComunidad(nombre)
    }


}