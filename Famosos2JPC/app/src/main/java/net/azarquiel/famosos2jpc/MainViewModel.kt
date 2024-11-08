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

    /*
    Hacemos un mutableStateListOf VACIO. Si le colocas colores, se crea un bucle infinito.
    Luego hay que hacer un .clear() en cada nuevo intento para evitar el mismo problema
    Y el primer bucle para pintarlo de gris debe ser con .add
    Luego ya sí se pueden asignar los colores con =
     */
    private val _coloresFotos = mutableStateListOf<Color>()
    val coloresFotos: List<Color> = _coloresFotos
    private val _coloresNombres = mutableStateListOf<Color>()
    val coloresNombres: List<Color> = _coloresNombres


    //Array de Famosos como objetos para poder separar id de nombre
    var famososArray = Array(600) { Famoso() }
    //Array de Famosos que se utilizarán en la jugada actual mutable
    private val _jugadaArray = MutableLiveData<Array<Famoso>>()
    var jugadaArray : LiveData<Array<Famoso>> = _jugadaArray
    //Variable necesaria para el json
    var famosojson = ""
    //Variables necesarias para el intento actual
    var nombre: String? = null
    var id = ""
    //Variable que permite reiniciar el juego cuando acierten todos
    var aciertos = 0
    init {
        //Si no existe el fichero, lo crea
        Util.inyecta(mainActivity.baseContext, "person.xml")
        //Inicializa el array de la jugada
        _jugadaArray.value = Array(5) { Famoso() }
        newGame()
    }
    private fun getAllFamosos() {
        //Lee el fichero person.xml y lo convierte en un array de Famosos
        val sh = mainActivity.getSharedPreferences("person", Context.MODE_PRIVATE)
        val famososSH = sh.all
        var i = 0
        //Recorre el array de Famosos y los convierte en objetos
        famososSH.forEach {(key, value) ->
            famosojson = value.toString()
            val famoso = Gson().fromJson(famosojson, Famoso::class.java)
            //Añade el objeto a la lista de famosos
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
        //Si el color de la foto es verde, no hace nada
        if (_coloresFotos[i] == Color.Green) return
        //Obtenemos el indice de la foto pulsada para cambiar su color en el pulsado2
        _n.value =i
        //Cambiamos el color de la foto pulsada
        _coloresFotos[i] = Color.Yellow
        //Obtenemos el id y el nombre de la foto pulsada para compararlos en el pulsado2
        id = famososArray[i].id
        nombre = famososArray[i].nombre
        //Mostramos un toast con el id y el nombre de la foto pulsada
        //Toast.makeText(mainActivity, "Pulsado $id y $nombre", Toast.LENGTH_SHORT).show()


    }
    fun pulsado2(i: Int) {
        //Si el color del nombre es verde, no hace nada
        if (_coloresNombres[i] == Color.Green) return
        //Si el nombre es null, no hace nada. Esto se debe a que no se ha pulsado ninguna foto
        if (nombre == null) return
        //Comparamos el nombre pulsado con el nombre de la foto pulsada. Si se acierta:
        if (nombre == _jugadaNombres.value!![i]) {
            //Cambiamos el color del nombre pulsado  y del fondo de la foto pulsada a verde
            _coloresFotos[_n.value!!] = Color.Green
            _coloresNombres[i] = Color.Green
            //Aumentamos el contador de aciertos
            aciertos++
            //Toast.makeText(mainActivity, "Correcto", Toast.LENGTH_SHORT).show()
            //Si se han acertado 5, reiniciamos el juego
            if (aciertos == 5) {
                newGame()
            }
        } else {
            //Si no se acierta, cambiamos el color del nombre pulsado a rojo y del fondo de la foto pulsada a rojo
            _coloresFotos[_n.value!!] = Color.Red
            _coloresNombres[i] = Color.Red
            //Toast.makeText(mainActivity, "Incorrecto", Toast.LENGTH_SHORT).show()
        }
        //Reiniciamos el nombre para que no se pueda volver a comparar sin pulsar otro foto antes
        nombre = null


    }

    private fun newIntento() {
        //Mezclamos el array de famosos
        famososArray.shuffle()
        /*
        Esto es IMPORTANTE. Es NECESARIO limpiar los colores de las fotos y nombres.
        Si no, puede generar el bucle infinito.
         */
        _coloresFotos.clear()
        _coloresNombres.clear()
        //Añadimos 5 colores al array de colores de las fotos y nombres
        //Es IMPORTANTE NO ASIGNARLOS CON =, sino con .add
        for (i in 0 until 5) {
            _coloresFotos.add(Color.LightGray)
            _coloresNombres.add(Color.LightGray)
        }

        //Variable necesarias para obtener el id y el nombre de la foto
        var id = 0
        var nombres = Array(5) { "" }
        //Vamos a coger 5 elementos del array de famosos y los vamos a poner en el array de la jugada
        for (i in 0 until 5) {
            _jugadaArray.value!![i] = famososArray[i]
            //Paso necesario para obtener el id de la foto
            id = mainActivity.resources.getIdentifier(
                "p${jugadaArray.value!![i].id}",
                "drawable",
                mainActivity.packageName
            )
            //Añadimos el id de la foto
            _jugadaFotos.value!![i] = id
            /*
            Añadimos el nombre de la 2 columna. Podemos usar el array de famosos porque
            son los mismos que el array de jugadas, y como están convertidos en objetos,
            es más facil obtener el nombre
             */
            nombres[i] = famososArray[i].nombre
        }
        //Mezclamos el array de nombres
        nombres.shuffle()
        //Asignamos el array de nombres a la variable de la vista
        _jugadaNombres.value = nombres
    }
}
