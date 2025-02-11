package net.azarquiel.chistesapp.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.chistesapp.R
import net.azarquiel.chistesapp.adapters.ChistesAdapter
import net.azarquiel.chistesapp.databinding.ActivityMainBinding
import net.azarquiel.chistesapp.model.Categoria
import net.azarquiel.chistesapp.viewmodel.DataViewModel

class ChistesActivity : AppCompatActivity() {

    private lateinit var categoria: Categoria
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ChistesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chistes)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolbar)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rvchiste)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rvchistesrow = findViewById<RecyclerView>(R.id.rvchiste)
        categoria = intent.getSerializableExtra("categoria") as Categoria
        initRV()

        val viewModel = ViewModelProvider(this)[DataViewModel::class.java]
        viewModel.getChistesPorCategoria(categoria.id).observe(this) {
            it?.let {

                adapter.setChistes(it)

            }
        }


    }
        private fun initRV() {
            adapter = ChistesAdapter(this, R.layout.rowchiste)
            binding.cm.rvcategorias.layoutManager = LinearLayoutManager(this)
            binding.cm.rvcategorias.adapter = adapter
        }

}