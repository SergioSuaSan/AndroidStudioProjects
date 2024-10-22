package net.azarquiel.signstraficojpc

import android.content.Context
import android.os.SystemClock
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.signstraficojpc.mode.Signal
import net.azarquiel.signstraficojpc.util.Util
import kotlinx.coroutines.Dispatchers.Main as Main

class MainViewModel(mainActivity: MainActivity) : ViewModel() {


    private val _signBuscar: MutableLiveData<Signal> = MutableLiveData()
    val signBuscar: LiveData<Signal> = _signBuscar
    private val _jugadaFotos: MutableLiveData<IntArray> = MutableLiveData(IntArray(4))
    val jugadaFotos: LiveData<IntArray> = _jugadaFotos
    private val _intentos: MutableLiveData<Int> = MutableLiveData(1)
    val intentos: LiveData<Int> = _intentos
    private val _aciertos: MutableLiveData<Int> = MutableLiveData(0)
    val aciertos: LiveData<Int> = _aciertos
    private val _openDialog = MutableLiveData(false)
    val openDialog: LiveData<Boolean> = _openDialog
    private val _msg: MutableLiveData<String> = MutableLiveData("")
    val msg: LiveData<String> = _msg
    private val _color: MutableLiveData<Color> = MutableLiveData(Color.White)
    val color: LiveData<Color> = _color

    private var azar: Int = 0
    val mainActivity by lazy { mainActivity }

    val signsArray = Array<Signal>(400) { Signal()}
    val jugadaArray = Array<Signal>(4) { Signal()}




    init {
        Util.inyecta(mainActivity.baseContext, "signs.xml")
        newGame()
    }

    private fun getAllSigns() {
        val sh = mainActivity.getSharedPreferences("signs", Context.MODE_PRIVATE)
        val signsSH = sh.all
        var i = 0
        signsSH.forEach {(key, value) ->
            signsArray[i] = Signal(key, value.toString())
            i++
        }
    }

    fun onCLickSign(i: Int) {
        if (i == azar) {
            Toast.makeText(mainActivity, "Correcto", Toast.LENGTH_SHORT).show()
            _aciertos.value = _aciertos.value!! + 1
            _color.value = Color.Green
        } else {
            Toast.makeText(mainActivity, "Incorrecto", Toast.LENGTH_SHORT).show()
            _color.value = Color.Red
        }
        GlobalScope.launch {
            SystemClock.sleep(200)
            launch(Main) {
                if (_intentos.value!! < 10) {
                    _intentos.value = _intentos.value!! + 1
                    newIntento()
                } else {
                    gameOver()

                }
            }
        }


    }

    fun newGame() {
        getAllSigns()
        newIntento()
        _aciertos.value = 0
        _intentos.value = 1
        _color.value = Color.White

    }
    private fun gameOver() {
        _msg.value = "Has conseguido ${_aciertos.value} aciertos de 10 intentos"
        _openDialog.value = true

    }
    fun setDialog(open: Boolean) {
        _openDialog.value = open
    }



    private fun newIntento() {
        signsArray.shuffle()
        var id = 0
        for (i in 0 until 4) {
            jugadaArray[i] = signsArray[i]
            id =   mainActivity.resources.getIdentifier( "s${jugadaArray[i].id}", "drawable", mainActivity.packageName)
            _jugadaFotos.value!![i] = id
        }
        azar = (0..3).random()
        _signBuscar.value = jugadaArray[azar]
        _color.value = Color.White

    }


}
