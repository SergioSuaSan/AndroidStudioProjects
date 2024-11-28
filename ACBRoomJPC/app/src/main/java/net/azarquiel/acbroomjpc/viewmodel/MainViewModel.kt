package net.azarquiel.acbroomjpc.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.azarquiel.acbroomjpc.R
import net.azarquiel.acbroomjpc.model.Jugador
import net.azarquiel.acbroomjpc.model.JugadorWE
import net.azarquiel.acbroomjpc.view.MainActivity
import net.azarquiel.alltricks.util.Util

class MainViewModel(mainActivity: MainActivity) : ViewModel() {


    private var jugadorViewModel: JugadorViewModel
    private val _jugadores: MutableLiveData<List<JugadorWE>> = MutableLiveData()
    val jugadores: LiveData<List<JugadorWE>> = _jugadores

    private val _idIcoFilter: MutableLiveData<Int> = MutableLiveData(R.drawable.player)
    val idIcoFilter: LiveData<Int> = _idIcoFilter

    private val _likestate: MutableLiveData<Int> = MutableLiveData()
    val likestate: LiveData<Int> = _likestate

    private val _isPlayer: MutableLiveData<Boolean> = MutableLiveData(true)
    val isPlayer: LiveData<Boolean> = _isPlayer
    init {
        Util.inyecta(mainActivity, "acb.sqlite")
        jugadorViewModel = ViewModelProvider(mainActivity).get(JugadorViewModel::class.java)

        jugadorViewModel.getJugadores().observe(mainActivity, Observer { jugadores ->
            _jugadores.value = jugadores
        })
    }
    fun changeIcoFilter() {
        _isPlayer.value = !_isPlayer.value!!
        _idIcoFilter.value = if (_idIcoFilter.value == R.drawable.player)
                                 R.drawable.team
                             else
                                 R.drawable.player
    }

    fun addLike(jugador: Jugador) {
        jugador.likes +=1
        jugadorViewModel.update(jugador)

    }
}