package net.azarquiel.famosos2jpc


import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
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
    private val _n = MutableLiveData(1)
    private val _coloresFotos = mutableStateListOf(Color.Cyan, Color.Cyan, Color.Cyan, Color.Cyan, Color.Cyan)
    val coloresFotos: SnapshotStateList<Color> = _coloresFotos

    private val _coloresNombres = mutableStateListOf(Color.Cyan, Color.Cyan, Color.Cyan, Color.Cyan, Color.Cyan)
    val coloresNombres: SnapshotStateList<Color> = _coloresNombres




    var famososArray = Array(600) { Famoso() }
    private val _jugadaArray = MutableLiveData<Array<Famoso>>()
    var jugadaArray : LiveData<Array<Famoso>> = _jugadaArray
    var famosojson = ""
    var nombre: String? = null
    var id = ""
    var aciertos = 0
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
        aciertos = 0
    }


    fun pulsado1(i: Int) {
<<<<<<< Updated upstream
//        _n.value =i
       coloresFotos[i] = Color.Blue
//        id = famososArray[i].id
//        nombre = famososArray[i].nombre
//        Toast.makeText(mainActivity, "Pulsado $id y $nombre", Toast.LENGTH_SHORT).show()
=======
        _n.value =i
    //    _coloresFotos[i] = Color.Blue
        id = famososArray[i].id
        nombre = famososArray[i].nombre
        Toast.makeText(mainActivity, "Pulsado $id y $nombre", Toast.LENGTH_SHORT).show()
>>>>>>> Stashed changes

    }
    fun pulsado2(i: Int) {
        Log.d("SERGIO", "SUFRIR")
        if (nombre == null) return
        if (nombre == _jugadaNombres.value!![i]) {
            _coloresFotos[_n.value!!] = Color.Green
            _coloresNombres[i] = Color.Green
            aciertos++
            Toast.makeText(mainActivity, "Correcto", Toast.LENGTH_SHORT).show()
            if (aciertos == 5) {
                newGame()
            }
        } else {
            _coloresFotos[_n.value!!] = Color.Red
            _coloresNombres[i] = Color.Red
            Toast.makeText(mainActivity, "Incorrecto", Toast.LENGTH_SHORT).show()
        }
        nombre = null


    }
    /*
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
    */

    private fun newIntento() {
        famososArray.shuffle()
        for (i in 0 until 5) {
            coloresFotos[i] = Color.LightGray
            coloresNombres[i] = Color.LightGray
        }

        var id = 0
        var nombres = Array(5) { "" }
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
