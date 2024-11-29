package net.azarquiel.agendaroomjpc.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.agendaroomjpc.AmigoRepository
import net.azarquiel.agendaroomjpc.model.Amigo

class AmigoViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: AmigoRepository = AmigoRepository(application)

    fun getAllProdutcs(): LiveData<List<Amigo>>{
        return repository.getAllAmigos()
    }


    fun insert(Amigo: Amigo) {
        GlobalScope.launch() {
            repository.insert(Amigo)
            launch(Dispatchers.Main) {
            }
        }
    }

    fun delete(id: Int) {
        GlobalScope.launch() {
            repository.delete(id)
            launch(Dispatchers.Main) {
            }
        }
    }

    fun update(Amigo: Amigo) {
        GlobalScope.launch() {
            repository.update(Amigo)
            launch(Dispatchers.Main) {
            }
        }
    }
}
