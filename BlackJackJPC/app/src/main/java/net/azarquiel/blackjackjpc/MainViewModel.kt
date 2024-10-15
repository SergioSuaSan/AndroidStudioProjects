package net.azarquiel.blackjackjpc

import android.os.SystemClock
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.azarquiel.blackjackjpc.model.Carta
import kotlin.random.Random

class MainViewModel(mainActivity: MainActivity) : ViewModel(){

    private var mainActivity = mainActivity
    private val _cartas = mutableStateListOf<Carta>()
    val cartas: SnapshotStateList<Carta> = _cartas

    private val _titulo = MutableLiveData<String>()
    val titulo: LiveData<String> = _titulo

    //Acabamos de crear un mazo con 40 objetos carta vacíos
    val mazo = Array(40) { Carta() }
    val palos = arrayOf("clubs", "diamonds", "hearts", "spades")
    val random = Random(System.currentTimeMillis())
    val numeros = arrayOf("As", "2", "3", "4", "5", "6", "7", "J", "Q", "K")
    var posmazo = 0
    var puntos = Array(2){0}
    var jugador = 0;
    var durmiendo = false
    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg
    private val _openDialog = MutableLiveData<Boolean>()
    val openDialog: LiveData<Boolean> = _openDialog



    init {
        crearMazo()
        sacaCarta()
        sacaCarta()
    }

    fun crearMazo(){
        var i = 0
        for (palo in 0 until 4) {
            for (numero in 1 .. 10) {
                mazo[i] = Carta(numero, palo)
                i++
            }
        }
        mazo.shuffle(random)
    }
    fun sacaCarta() {
        val carta = mazo[posmazo]
        _cartas.add(0,carta)
        posmazo++
        puntos[jugador] += if (carta.numero > 7) 10 else carta.numero
        _titulo.value = "P${jugador + 1}: ${puntos[jugador]}"
        if (puntos[jugador] > 21) {

            _cartas.clear()
            _titulo.value = ""
           nextPlayer()

        }

    }

     fun nextPlayer() {

         if (durmiendo) return
         _cartas.clear()
         _titulo.value = ""
         durmiendo = true
        if (jugador == 0) {
            Toast.makeText(mainActivity.baseContext, "Pasa el movil por favor...", Toast.LENGTH_LONG).show()
            GlobalScope.launch {
                SystemClock.sleep(3000)
                launch(Main) {
                    durmiendo = false
                    jugador = 1
                    sacaCarta()
                    sacaCarta()
                }
            }


        } else {
            checkWinner()
        }
    }

    private fun checkWinner() {
        if (puntos[0] > 21 && puntos[1] > 21) {
            _msg.value = "Tablas"
        } else if (puntos[0] > 21 && puntos[1] <= 21) {
            _msg.value = "Gana el jugador 2 \n\n P1 (${puntos[0]})- P2 (${puntos[1]})"
        } else if (puntos[0] <= 21 && puntos[1] > 21) {
            _msg.value = "Gana el jugador 1 \n\n P1 (${puntos[0]})- P2 (${puntos[1]})"
        } else if (puntos[0] > puntos[1]) {
            _msg.value = "Gana el jugador 1 \n\n P1 (${puntos[0]})- P2 (${puntos[1]})"
        } else if (puntos[0] < puntos[1]) {
            _msg.value = "Gana el jugador 2 \n\n P1 (${puntos[0]})- P2 (${puntos[1]})"
        } else {
            _msg.value = "Tablas"
        }
        _openDialog.value = true
        Toast.makeText(mainActivity.baseContext, _msg.value, Toast.LENGTH_LONG).show()
    }




}
