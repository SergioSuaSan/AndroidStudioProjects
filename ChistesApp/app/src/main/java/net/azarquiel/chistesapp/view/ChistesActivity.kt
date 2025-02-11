package net.azarquiel.chistesapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
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
import net.azarquiel.chistesapp.model.Chiste
import net.azarquiel.chistesapp.viewmodel.DataViewModel

class ChistesActivity : AppCompatActivity() {
    private lateinit var rvchistes: RecyclerView
    private lateinit var categoria: Categoria
    private lateinit var adapter: ChistesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chistes)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rvchiste)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rvchistes = findViewById<RecyclerView>(R.id.rvchiste)
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
            rvchistes.layoutManager = LinearLayoutManager(this)
            rvchistes.adapter = adapter
        }

    fun onClickChiste( v: View) {
        val chistePulsado = v.tag as Chiste
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("chiste", chistePulsado)
        intent.putExtra("categoria", categoria)
        startActivity(intent)

    }

}