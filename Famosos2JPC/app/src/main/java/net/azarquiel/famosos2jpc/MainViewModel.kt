package net.azarquiel.famosos2jpc


import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.azarquiel.bingoshare.util.Util
import net.azarquiel.famosos2jpc.model.Famoso

class MainViewModel(mainActivity: MainActivity): ViewModel() {

    val mainActivity by lazy { mainActivity }
    private val _color = MutableLiveData<Color>()
    val color: LiveData<Color> = _color
    private val _aciertos = MutableLiveData<Int>()
    val aciertos: LiveData<Int> = _aciertos
    private val _intentos = MutableLiveData<Int>()
    val intentos: LiveData<Int> = _intentos
    private val _tiempo = MutableLiveData<Long>()
    val tiempo: LiveData<Long> = _tiempo
    private val _famosoBuscar = MutableLiveData<Famoso>()
    val famosoBuscar: LiveData<Famoso> = _famosoBuscar
    private val _jugadaFotos = MutableLiveData<Array<Int>>()
    val jugadaFotos: LiveData<Array<Int>> = _jugadaFotos

    var famososArray = Array<Famoso>(600) { Famoso() }
    var jugadaArray = Array<Famoso>(5) { Famoso() }
    private var azar: Int = 0


    init {
        Util.inyecta(mainActivity.baseContext, "person.xml")
        newGame()
    }


    private fun getAllFamosos() {
        val sh = mainActivity.getSharedPreferences("person", Context.MODE_PRIVATE)
        val signsSH = sh.all
        var i = 0
        signsSH.forEach {(key, value) ->
            famososArray[i] = Famoso(key, value.toString())
            i++
        }
    }

    private fun newGame() {
        _aciertos.value = 0
        _intentos.value = 0
        getAllFamosos()
        newIntento()
    }


    fun pulsado() {
        TODO("Not yet implemented")


    }
    private fun newIntento() {
        famososArray.shuffle()
        var id = 0
        for (i in 0 until 5) {
            jugadaArray[i] = famososArray[i]
            id = jugadaArray[i].id.toInt()

        }
        azar = (0..3).random()
        _famosoBuscar.value = jugadaArray[azar]
        _color.value = Color.White

    }
}
