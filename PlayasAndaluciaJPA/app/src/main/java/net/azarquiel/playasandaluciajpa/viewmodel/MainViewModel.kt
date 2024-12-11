package net.azarquiel.playasandaluciajpa.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.azarquiel.alltricks.util.Util
import net.azarquiel.playasandaluciajpa.MainActivity
import net.azarquiel.playasandaluciajpa.model.Costa
import net.azarquiel.playasandaluciajpa.model.PlayawCosta

class MainViewModel(mainActivity: MainActivity) : ViewModel() {

    val mainActivity = mainActivity
    val costaViewModel = ViewModelProvider(mainActivity).get(CostaViewModel::class.java)
    val playaViewModel = ViewModelProvider(mainActivity).get(PlayasViewModel::class.java)

    private val _costas: MutableLiveData<List<Costa>> = MutableLiveData()
    val costas: LiveData<List<Costa>> = _costas
    private val _playasByCosta: MutableLiveData<List<PlayawCosta>> = MutableLiveData()
    val playasByCosta: LiveData<List<PlayawCosta>> = _playasByCosta
    private val _playas: MutableLiveData<PlayawCosta> = MutableLiveData()
    val playas: LiveData<PlayawCosta> = _playas
    private val _azul: MutableLiveData<Int> = MutableLiveData(0)
    val azul: LiveData<Int> = _azul



    init {
        Util.inyecta(mainActivity, "playasandalu.db")
        costaViewModel.getAllCostas().observe(mainActivity) { costasDatos -> //Bendito Manu que me ha quitado el conflicto con "comunidades"
            _costas.value = costasDatos
            costasDatos.forEach { costas ->
                Log.d("Sergio", costas.toString())

            }
        }

    }

    fun getPlayabyCosta(id:Int)  {
        playaViewModel.getPlayasbyCosta(id).observe(mainActivity) { playasDatos ->
            _playasByCosta.value = playasDatos
            playasDatos.forEach { playa ->
                 Log.d("Sergio", playa.toString())
            }
        }

    }
    fun getFavPlayabyCosta(id:Int)  {
        playaViewModel.getFavPlayasbyCosta(id).observe(mainActivity) { playasDatos ->
            _playasByCosta.value = playasDatos
            playasDatos.forEach { playa ->
                Log.d("Sergio", playa.toString())
            }
        }

    }

    fun clicadoCosta(id: Int){
        Log.d("Sergio", "clicadoCosta: $id")

        getPlayabyCosta(id)

    }

    fun changeIconFilter() {
        if (_azul.value == 0) {
            _azul.value = 1

        } else {
            _azul.value = 0

        }
    }


}


