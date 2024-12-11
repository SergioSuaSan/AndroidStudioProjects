package net.azarquiel.examenplantilla.viewmodel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PuebloVM (application: Application) : AndroidViewModel(application)
{
//    private var repository: PuebloRepository = PuebloRepository(application)

//    fun update(pueblo: Pueblo)
//    {
//        GlobalScope.launch() {
//            repository.update(pueblo)
//            launch(Dispatchers.Main) {
//            }
//        }
//    }
//
//    fun getPueblosByComunidad(idcomunidad:Int): LiveData<List<PuebloWithProvincia>>
//    {
//        return repository.getPueblosByComunidad(idcomunidad)
//    }
//    fun getPueblosFavByComunidad(idcomunidad:Int): LiveData<List<PuebloWithProvincia>>
//    {
//        return repository.getPueblosFavByComunidad(idcomunidad)
//    }
}
