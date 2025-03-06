package net.azarquiel.gafasretrofitjpc.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import net.azarquiel.gafasretrofitjpc.model.Comentario
import net.azarquiel.gafasretrofitjpc.model.Usuario
import net.azarquiel.gafasretrofitjpc.view.MainActivity
import net.azarquiel.gafasretrofitjpc.model.Gafa
import net.azarquiel.gafasretrofitjpc.model.Marca

class MainViewModel(mainActivity: MainActivity) {


    private val mainActivity: MainActivity = mainActivity
    private val _gafas: MutableLiveData<List<Gafa>> = MutableLiveData()
    val gafas: LiveData<List<Gafa>> = _gafas
    private val _marcas: MutableLiveData<List<Marca>> = MutableLiveData()
    val marcas: LiveData<List<Marca>> = _marcas
    private val _comentarios: MutableLiveData<List<Comentario>> = MutableLiveData()
    val comentarios: LiveData<List<Comentario>> = _comentarios
    private val _usuarios: MutableLiveData<List<Usuario>> = MutableLiveData()
    val usuarios: LiveData<List<Usuario>> = _usuarios
    private val _usuario: MutableLiveData<Usuario> = MutableLiveData()
    val usuario: LiveData<Usuario> = _usuario
    private val _openDialog = MutableLiveData(false)
    val openDialog: LiveData<Boolean> = _openDialog
    private val _msg: MutableLiveData<String> = MutableLiveData("")
    val msg: LiveData<String> = _msg

    var dataviewModel = ViewModelProvider(mainActivity)[DataViewModel::class.java]

    init {
        dataviewModel = ViewModelProvider(mainActivity)[DataViewModel::class.java]
        dataviewModel.getMarcas().observe(mainActivity) {
            it?.let {
                _marcas.value = it
            }
        }
    }

    fun getGafasbyMarca(idMarca: Int) {
        dataviewModel = ViewModelProvider(mainActivity)[DataViewModel::class.java]
        dataviewModel.getGafasbyMarca(idMarca).observe(mainActivity) {
            it?.let {
                _gafas.value = it
            }


        }
    }

    fun getDataComentarios(idRecurso: Int) {
        dataviewModel = ViewModelProvider(mainActivity)[DataViewModel::class.java]
        dataviewModel.getDataComentarios(idRecurso).observe(mainActivity) {
            it?.let {
                _comentarios.value = it
            }
        }
    }




    // Función para establecer el usuario
    fun setUsuario(usuario: Usuario) {
        _usuario.value = usuario
    }





    fun setDialog(open: Boolean) {
        _openDialog.value = open
    }

    fun saveComentario(it: String) {
        _msg.value = it
    }

    fun insertarComentario(gafa: Gafa, idUsuario: Int, fecha: String, comentario: String) {
        dataviewModel = ViewModelProvider(mainActivity)[DataViewModel::class.java]
        if (_usuario.value != null && _usuario.value!!.idusuario > 0) {
            dataviewModel.saveComentario(gafa.id, idUsuario, fecha, comentario)
                .observe(mainActivity) {
                    // ✅ Obtener la lista actual de comentarios
                    val listaActual = _comentarios.value.orEmpty().toMutableList()

                    val comentario =
                        Comentario(-1, gafa.id, _usuario.value!!.nick, fecha, comentario)
                    // ✅ Añadir el nuevo comentario a la lista
                    listaActual.add(comentario)

                    // ✅ Actualizar el LiveData con la nueva lista
                    _comentarios.value = listaActual
                }
        }

    }


    fun login(nick: String, pass: String) {
        dataviewModel = ViewModelProvider(mainActivity)[DataViewModel::class.java]
        Log.d("TAG", "login: $nick, $pass")
        if (nick != "" && pass != "") {
            Log.d("TAG", "login ACABO DE ENTRAR: $nick, $pass")
            dataviewModel.getDataUsuarioPorNickPass(nick, pass)
                .observe(mainActivity) { usuarioExistente ->
                    Log.d("TAG", "login: $usuarioExistente")
                    if (usuarioExistente != null && usuarioExistente.idusuario != 0) {
                        _usuario.postValue(usuarioExistente)
                        mainActivity.saveUsuario(usuarioExistente)
                        Log.d("TAG", "Usuario encontrado en API: $usuarioExistente")
                    } else {
                        // Si no existe, lo creamos
                        val nuevoUsuario = Usuario(-1, -1, nick, pass)
                        dataviewModel.saveUsuario(nuevoUsuario)
                            .observe(mainActivity) { usuarioCreado ->
                                Log.d("TAG", "Usuario creado: $usuarioCreado")
                                usuarioCreado?.let {
                                    _usuario.postValue(it)
                                    mainActivity.saveUsuario(it)
                                    Log.d("TAG", "Nuevo usuario creado: $it")
                                }

                            }
                    }

                }

        }
    }


/*
    fun logout() {
        _usuario.value = Usuario(-1, "", "")
    }
*/


}



