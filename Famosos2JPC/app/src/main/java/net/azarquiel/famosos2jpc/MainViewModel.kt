package net.azarquiel.famosos2jpc


import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import net.azarquiel.bingoshare.util.Util
import net.azarquiel.famosos2jpc.model.Famoso

class MainViewModel(mainActivity: MainActivity): ViewModel() {

    val mainActivity by lazy { mainActivity }

    private val _jugadaFotos: MutableLiveData<IntArray> = MutableLiveData(IntArray(5))
    val jugadaFotos: LiveData<IntArray> = _jugadaFotos
    private val _jugadaNombres = MutableLiveData(Array(5) { "" })
    val jugadaNombres: LiveData<Array<String>> = _jugadaNombres

    var famososArray = Array(600) { Famoso() }
    private val _jugadaArray = MutableLiveData<Array<Famoso>>()
    var jugadaArray : LiveData<Array<Famoso>> = _jugadaArray
    var famosojson = ""
    var nombre = ""
    var id = ""
    var contador = 0



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
        getAllFamosos()
        newIntento()
    }


    fun pulsado(i: Int) {

        if (contador == 0) {
            id = famososArray[i].id
            nombre = famososArray[i].nombre
            Toast.makeText(mainActivity, "Pulsado $id y $nombre", Toast.LENGTH_SHORT).show()
            contador++

        } else {
            id = _jugadaArray.value!![i].id

            contador--
            if (nombre == _jugadaNombres.value!![i]) {
                Toast.makeText(mainActivity, "Correcto", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(mainActivity, "Incorrecto", Toast.LENGTH_SHORT).show()
            }


        }




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
