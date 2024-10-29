package net.azarquiel.carrojpc

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import net.azarquiel.carrojpc.model.Producto

class MainViewModel(mainActivity: MainActivity) : ViewModel() {


    private lateinit var carrosh: SharedPreferences
    val mainActivity by lazy { mainActivity }
    private val _openDialog = MutableLiveData(false)
    val openDialog: LiveData<Boolean> = _openDialog
    var productos = mutableStateListOf<Producto>()

    private val _color = MutableLiveData<Color>()
    val color: LiveData<Color> = _color
    private val _ispulsado = MutableLiveData<Boolean>()
    val ispulsado: LiveData<Boolean> = _ispulsado



    init {
        carrosh = mainActivity.getSharedPreferences("carro", Context.MODE_PRIVATE)
        getAllProductos()
    }
    fun setDialog(value: Boolean) {
        _openDialog.value = value
    }

    fun addProducto(producto: Producto) {
        producto.id = productos.size +1
        val jsonProducto = Gson().toJson(producto)
        val editor = carrosh.edit()
        editor.putString(producto.id.toString(), jsonProducto)
        editor.apply()

    }



    fun getAllProductos(){
        val carroAll =  carrosh.all
        productos.clear()
        for ((key,value) in  carroAll) {
            val jsonProducto = value.toString()
            val producto = Gson().fromJson(jsonProducto, Producto::class.java)
            productos.add(producto)
        }
    }



}
