package net.azarquiel.agendaroomjpc

import android.app.Application
import androidx.lifecycle.LiveData
import net.azarquiel.agendaroomjpc.model.Agendadb
import net.azarquiel.agendaroomjpc.model.Amigo

class AmigoRepository(application: Application) {

    val AmigoDao = Agendadb.getDatabase(application).AmigoDao()
    // select
    fun getAllAmigos(): LiveData<List<Amigo>> {
        return AmigoDao.getAllAmigos()
    }
    // insert
    fun insert(amigo: Amigo) {
        AmigoDao.insert(amigo)
    }
    // delete
    fun delete(id:Int) {
        AmigoDao.delete(id)
    }
    // update
    fun update(amigo: Amigo) {
        AmigoDao.update(amigo)
    }
}
