package net.azarquiel.avesretrofit.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import net.azarquiel.avesretrofit.model.Comentario
import net.azarquiel.avesretrofit.model.Recurso
import net.azarquiel.avesretrofit.model.Usuario
import net.azarquiel.avesretrofit.model.Zona
import net.azarquiel.avesretrofit.view.MainActivity

class MainViewModel(mainActivity: MainActivity) {


    private val mainActivity: MainActivity = mainActivity
    private val _zonas: MutableLiveData<List<Zona>> = MutableLiveData()
    val zonas: LiveData<List<Zona>> = _zonas
    private val _recursos: MutableLiveData<List<Recurso>> = MutableLiveData()
    val recursos: LiveData<List<Recurso>> = _recursos
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
        dataviewModel.getDataZonas().observe(mainActivity) {
            it?.let {
                _zonas.value = it
            }
        }
    }

    fun getDataRecursos(idZona: Int) {
        dataviewModel = ViewModelProvider(mainActivity)[DataViewModel::class.java]
        dataviewModel.getDataRecursos(idZona).observe(mainActivity) {
            it?.let {
                _recursos.value = it
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


    fun setDialog(open: Boolean) {
        _openDialog.value = open
    }

    fun saveComentario(it: String) {
        _msg.value = it
    }

    fun insertarComentario(idRecurso: Int, idUsuario: Int, fecha: String, comentario: String) {
        dataviewModel = ViewModelProvider(mainActivity)[DataViewModel::class.java]
        if (_usuario.value != null) {
            dataviewModel.saveComentario(idRecurso, idUsuario, fecha, comentario)
                .observe(mainActivity) {
                    // ✅ Obtener la lista actual de comentarios
                    val listaActual = _comentarios.value.orEmpty().toMutableList()

                    val comentario =
                        Comentario(-1, _usuario.value!!.nick, idRecurso, fecha, comentario)
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
            dataviewModel.getDataUsuarioPorNickPass(nick, pass)
                .observe(mainActivity) { usuarioExistente ->
                    if (usuarioExistente != null && usuarioExistente.idusuario != 0) {
                        _usuario.postValue(usuarioExistente)
                        Log.d("TAG", "Usuario encontrado en API: $usuarioExistente")
                    } else {
                        // Si no existe, lo creamos
                        val nuevoUsuario = Usuario(-1, nick, pass)
                        dataviewModel.saveUsuario(nuevoUsuario)
                            .observe(mainActivity) { usuarioCreado ->
                                usuarioCreado?.let {
                                    _usuario.postValue(it)
                                    Log.d("TAG", "Nuevo usuario creado: $it")
                                }
                            }
                    }

                }

        }
    }
}



