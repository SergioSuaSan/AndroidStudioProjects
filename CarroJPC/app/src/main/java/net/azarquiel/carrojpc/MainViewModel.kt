package net.azarquiel.carrojpc

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.gson.Gson
import net.azarquiel.carrojpc.model.Producto

class MainViewModel(mainActivity: MainActivity) : ViewModel() {


    private lateinit var carro: SharedPreferences
    val mainActivity by lazy { mainActivity }
    var productos = mutableListOf<Producto>()
    private val _openDialog = MutableLiveData(false)
    val openDialog: LiveData<Boolean> = _openDialog

    init {
        carro = mainActivity.getSharedPreferences("carro", Context.MODE_PRIVATE)
        getProductos()
    }
    fun setDialog(value: Boolean) {
        _openDialog.value = value
    }

    fun addProducto(producto: Producto) {
        productos.add(producto)
    }

    fun getProductos(){
        val carroSh =  carro.all
        productos = ArrayList<Producto>()
        for ((key,value) in  carroSh) {
            val jsonProducto = value.toString()
            val producto = Gson().fromJson(jsonProducto, Producto::class.java)
            productos.add(producto)
        }
    }



}
