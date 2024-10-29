package net.azarquiel.famosos2jpc


import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
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
    private val _jugadaFotos: MutableLiveData<IntArray> = MutableLiveData(IntArray(5))
    val jugadaFotos: LiveData<IntArray> = _jugadaFotos
    private val _jugadaNombres = MutableLiveData(Array(5) { "" })
    val jugadaNombres: LiveData<Array<String>> = _jugadaNombres

    var famososArray = Array<Famoso>(600) { Famoso() }
    private val _jugadaArray = MutableLiveData<Array<Famoso>>()
    var jugadaArray : LiveData<Array<Famoso>> = _jugadaArray
    var famosojson = ""



    init {
        Util.inyecta(mainActivity.baseContext, "person.xml")
        _jugadaArray.value = Array(5) { Famoso() }
        newGame()
    }


    private fun getAllFamosos() {
        val sh = mainActivity.getSharedPreferences("person", Context.MODE_PRIVATE)
        val famososSH = sh.all
        var i = 0
        famososSH.forEach {(key, value) ->

            famosojson = value.toString()
            val famoso = Gson().fromJson(famosojson, Famoso::class.java)
            famososArray[i] = famoso
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
        Toast.makeText(mainActivity, "Pulsado", Toast.LENGTH_SHORT).show()


    }
    private fun newIntento() {
        famososArray.shuffle()
        var id = 0
        var nombres = Array<String>(5) { "" }
        for (i in 0 until 5) {
            _jugadaArray.value!![i] = famososArray[i]
            id = mainActivity.resources.getIdentifier(
                "p${jugadaArray.value!![i].id}",
                "drawable",
                mainActivity.packageName
            )
            _jugadaFotos.value!![i] = id

            nombres[i] = famososArray[i].nombre


        }
        nombres.shuffle()
        _jugadaNombres.value = nombres


    }
}
