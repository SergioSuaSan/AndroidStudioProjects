package net.azarquiel.sumanumeros

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel(mainActivity: MainActivity) : ViewModel() {


    val mainActivity by lazy { mainActivity }
    private val _openDialog = MutableLiveData(false)
    val openDialog: LiveData<Boolean> = _openDialog
    private val _msg: MutableLiveData<String> = MutableLiveData("")
    val msg: LiveData<String> = _msg
    private val _color = MutableLiveData<Color>()
    val color: MutableLiveData<Color> = _color
    private val _tiempo = MutableLiveData<Long>()



    private val _aciertos = MutableLiveData<Int>()
    val aciertos: LiveData<Int> = _aciertos
    private val _intentos = MutableLiveData<Int>()
    val intentos: LiveData<Int> = _intentos
    private val _numero = MutableLiveData<Int>()
    val numero: LiveData<Int> = _numero
    private val _resultado = MutableLiveData<Int>()
    val resultado: LiveData<Int> = _resultado
    private val _num1 = MutableLiveData<Int>()
    val num1: LiveData<Int> = _num1
    private val _num2 = MutableLiveData<Int>()
    val num2: LiveData<Int> = _num2
    var operacion = 1



    private fun random(): Int {
        var random = Random(System.currentTimeMillis())
        var azar = (2..18).random(random)
        return azar
    }


    init {

      newGame()
    }




    fun onClick(n: Int) {
        if (operacion == 1) {
            _num1.value = n
            operacion++
        } else {
            _num2.value = n
            operacion--
            _resultado.value = num1.value!! + num2.value!!
            _color.value = Color.Red
            if (resultado.value == numero.value) {
                _aciertos.value = _aciertos.value?.plus(1)
                _color.value = Color.Green
            }
            _intentos.value = _intentos.value?.plus(1)
            if (_intentos.value == 5) {
                gameOver()
            }
            resetear()
        }


    }

    private fun resetear() {
        _numero.value = random()
        _num1.value = 0
        _num2.value = 0
        _resultado.value = 0
    }

    private fun gameOver() {
        _tiempo.value = (System.currentTimeMillis() - _tiempo.value!!)/1000
        _msg.value = "Has conseguido ${aciertos.value} aciertos en ${_tiempo.value} segundos"
        _openDialog.value = true
    }

    fun isOpenDialog(open: Boolean) {
        _openDialog.value = open
    }

    fun newGame() {
        _tiempo.value = System.currentTimeMillis()
        _openDialog.value = false
        _aciertos.value = 0
        _intentos.value = 0
        _numero.value = random()
        _color.value = Color.White

    }


}
