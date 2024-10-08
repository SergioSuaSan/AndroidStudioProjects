package net.azarquiel.blackjackjpc

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.azarquiel.blackjackjpc.model.Carta
import kotlin.random.Random

class MainViewModel : ViewModel(){

    private val _cartas = mutableStateListOf<Carta>()
    val cartas: SnapshotStateList<Carta> = _cartas
    //Acabamos de crear un mazo con 40 objetos carta vacíos
    val mazo = Array(40) { Carta() }
    val palos = arrayOf("clubs", "diamonds", "hearts", "spades")
    val random = Random(System.currentTimeMillis())
    val numeros = arrayOf("As", "2", "3", "4", "5", "6", "7", "J", "Q", "K")
    var posmazo = 0
    var puntos = 0


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
        puntos += carta.numero


    }

}
