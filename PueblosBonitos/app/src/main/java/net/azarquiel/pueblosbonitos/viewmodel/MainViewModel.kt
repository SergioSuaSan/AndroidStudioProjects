package net.azarquiel.pueblosbonitos.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.azarquiel.alltricks.util.Util
import net.azarquiel.pueblosbonitos.MainActivity
import net.azarquiel.pueblosbonitos.model.Comunidad
import net.azarquiel.pueblosbonitos.model.Pueblo
import net.azarquiel.pueblosbonitos.model.Pueblowp

class MainViewModel(mainActivity: MainActivity) : ViewModel() {

    val mainActivity = mainActivity

    private val _comunidades: MutableLiveData<List<Comunidad>> = MutableLiveData()
    val comunidades: LiveData<List<Comunidad>> = _comunidades
    private val _pueblosByComunidad: MutableLiveData<List<Pueblowp>> = MutableLiveData()
    val pueblosByComunidad: LiveData<List<Pueblowp>> = _pueblosByComunidad
    private val _pueblowp: MutableLiveData<Pueblowp> = MutableLiveData()
    val pueblowp: LiveData<Pueblowp> = _pueblowp
    private val _pulsadoBuscar: MutableLiveData<Boolean> = MutableLiveData(false)
    val pulsadoBuscar: LiveData<Boolean> = _pulsadoBuscar
    private val _fav: MutableLiveData<Int> = MutableLiveData(0)
    val fav: LiveData<Int> = _fav
    private val _colorEstrella: MutableLiveData<Color> = MutableLiveData(Color.Black)
    val colorEstrella: LiveData<Color> = _colorEstrella


    val comunidadViewModel = ViewModelProvider(mainActivity).get(ComunidadViewModel::class.java)
    val puebloViewModel = ViewModelProvider(mainActivity).get(PuebloViewModel::class.java)


    init {
         Util.inyecta(mainActivity, "pueblosbonitos.sqlite")
         comunidadViewModel.getAllComunidades().observe(mainActivity) { comunidadesDatos -> //Bendito Manu que me ha quitado el conflicto con "comunidades"
             _comunidades.value = comunidadesDatos
             comunidadesDatos.forEach { comunidad ->
                 Log.d("Sergio", comunidad.toString())

             }
         }

    }

    fun pulsadoBuscar(){
        _pulsadoBuscar.value = !_pulsadoBuscar.value!!
    }

    fun clicadoComunidad(comunidad: Comunidad) {
        Log.d("Sergio", "clicadoComunidad: $comunidad")

            getAllPueblowp(comunidad)



    }
    fun getAllPueblowp(comunidad: Comunidad) {
        puebloViewModel.getPueblowpByComunidad(comunidad.idComunidad).observe(mainActivity) { pueblosDatos ->
            _pueblosByComunidad.value = pueblosDatos
            pueblosDatos.forEach { pueblo ->
                // Log.d("Sergio", pueblo.toString())
            }
        }

    }
    fun getFavPueblowp(comunidad: Comunidad) {
        puebloViewModel.getPueblowpFavByComunidad(comunidad.nombreComunidad)
            .observe(mainActivity) { pueblosDatos ->
                _pueblosByComunidad.value = pueblosDatos
                pueblosDatos.forEach { pueblo ->
                    // Log.d("Sergio", pueblo.toString())
                }
            }
    }

        fun clicadoPueblo(pueblo: Pueblo) {
            Log.d("Sergio", "clicadoPueblo: $pueblo")
            puebloViewModel.getPueblowpDetail(pueblo.idPueblo)
                .observe(mainActivity) { pueblowpDatos ->
                    _pueblowp.value = pueblowpDatos

                }
            _colorEstrella.value = if (pueblo.fav == 0) Color.Black else Color.Yellow



        }


        fun changeFavPueblo(pueblo: Pueblo) {

            if (pueblo.fav == 0) {
                pueblo.fav = 1
                _colorEstrella.value = Color.Yellow
            } else {
                pueblo.fav = 0
                _colorEstrella.value = Color.Black
            }

            puebloViewModel.update(pueblo)

        }


        fun changeIconFilter() {
            if (_fav.value == 0) {
                _fav.value = 1

            } else {
                _fav.value = 0

            }
        }

}

