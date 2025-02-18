package net.azarquiel.chistesretrofitjpc.viewmodel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.azarquiel.chistesretrofitjpc.MainActivity
import net.azarquiel.chistesretrofitjpc.model.Categoria


class MainViewModel(mainActivity: MainActivity): ViewModel()  {
    private val _categorias: MutableLiveData<List<Categoria>> = MutableLiveData()
    val categorias: LiveData<List<Categoria>> = _categorias
    init {
        val dataviewModel = ViewModelProvider(mainActivity)[DataViewModel::class.java]
        dataviewModel.getCategorias().observe(mainActivity) {
            it?.let {
                _categorias.value = it
            }
        }
    }
}
