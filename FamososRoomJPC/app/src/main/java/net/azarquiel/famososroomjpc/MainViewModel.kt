package net.azarquiel.famososroomjpc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.azarquiel.famososroomjpc.model.Famoso
import net.azarquiel.famososroomjpc.util.Util
import net.azarquiel.famososroomjpc.viewmodel.FamosoViewModel

class MainViewModel(mainActivity: MainActivity): ViewModel()  {
    private val _famosos: MutableLiveData<List<Famoso>> = MutableLiveData()
    val famosos: LiveData<List<Famoso>> = _famosos
    init {
        val famosoViewModel = ViewModelProvider(mainActivity).get(FamosoViewModel::class.java)
        Util.inyecta(mainActivity, "famosos.db")
        famosoViewModel.getFamosos().observe(mainActivity, Observer { famosos ->
            _famosos.value = famosos
        })
    }
}
