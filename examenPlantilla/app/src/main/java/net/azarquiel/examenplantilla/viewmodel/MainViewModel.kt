package net.azarquiel.examenplantilla.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.azarquiel.examen.util.Util
import net.azarquiel.examenplantilla.MainActivity


class MainViewModel(mainActivity: MainActivity): ViewModel()
{
    private val mainActivity: MainActivity by lazy { mainActivity }
//    private var pueblosAll: List<PuebloWithProvincia> = emptyList()
//    private var pueblosFav: List<PuebloWithProvincia> = emptyList()
//    private val _comunidades: MutableLiveData<List<Comunidad>> = MutableLiveData()
//    val comunidades: LiveData<List<Comunidad>> = _comunidades
//    private val _colorfav: MutableLiveData<Int> = MutableLiveData(R.color.Gris)
//    val colorfav: LiveData<Int> = _colorfav
//    private val _isFav: MutableLiveData<Boolean> = MutableLiveData(false)
//    val isFav: LiveData<Boolean> = _isFav
//    private lateinit var puebloVM: PuebloVM

//    init {
//        Util.inyecta(mainActivity, "pueblosbonitos.sqlite")
//        val comunidadVM = ViewModelProvider(mainActivity).get(ComunidadVM::class.java)
//
//        comunidadVM.getAllComunidades().observe(mainActivity) { products ->
//            _comunidades.value = products
//        }
//    }
//    fun getPueblos(idcomunidad: Int) {
//        getPueblosAll(idcomunidad)
//        getPueblosFav(idcomunidad)
//        _isFav.value = true
//        _pueblos.value = pueblosAll
//    }
//
//    fun getPueblosFav(idcomunidad: Int) {
//        puebloVM = ViewModelProvider(mainActivity).get(PuebloVM::class.java)
//        puebloVM.getPueblosFavByComunidad(idcomunidad).observe(mainActivity) { pueblos ->
//            pueblosFav = pueblos
//        }
//        pueblosFav.forEach {
//        }
//    }
//
//    fun getPueblosAll(idcomunidad:Int) {
//        puebloVM = ViewModelProvider(mainActivity).get(PuebloVM::class.java)
//        puebloVM.getPueblosByComunidad(idcomunidad).observe(mainActivity) { pueblos ->
//            pueblosAll = pueblos
//        }
//    }
//    fun changeFav() {
//        if (isFav.value!!) {
//            _isFav.value = false
//            _colorfav.value = R.color.amarillo
//            _pueblos.value = pueblosFav
//        } else {
//            _isFav.value = true
//            _colorfav.value = R.color.Gris
//            _pueblos.value = pueblosAll
//        }
//    }
//
//    fun changePuebloFav(pueblo: PuebloWithProvincia) {
//        if (pueblo.pueblo.fav ==1){
//            pueblo.pueblo.fav = 0
//            puebloVM.update(pueblo.pueblo)
//        }else{
//            pueblo.pueblo.fav = 1
//            puebloVM.update(pueblo.pueblo)
//        }
//    }
}

