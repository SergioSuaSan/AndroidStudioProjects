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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import net.azarquiel.chistesapp.R
import net.azarquiel.chistesapp.adapters.CategoriaAdapter
import net.azarquiel.chistesapp.adapters.ChisteAdapter
import net.azarquiel.chistesapp.model.Categoria
import net.azarquiel.chistesapp.model.Chiste
import net.azarquiel.chistesapp.viewmodel.DataViewModel

class ChistesActivity : AppCompatActivity() {
    private lateinit var rvchistes: RecyclerView
    private lateinit var adapter: ChisteAdapter
    private lateinit var categoria: Categoria

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chistes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            dialogNewChiste()
        }
        rvchistes = findViewById<RecyclerView>(R.id.rvchistes)

        categoria = intent.getSerializableExtra("categoria") as Categoria
        initRV()

        val viewModel = ViewModelProvider(this)[DataViewModel::class.java]
        viewModel.getChistesByCategoria(categoria.id).observe(this) {
            it?.let {
                adapter.setChistes(it)
            }
        }

    }

    private fun dialogNewChiste() {

    }

    private fun initRV() {
        adapter = ChisteAdapter(this, R.layout.rowchiste)
        rvchistes.layoutManager = LinearLayoutManager(this)
        rvchistes.adapter = adapter
    }
    fun onClickChiste(v: View) {
        val chistePulsado = v.tag as Chiste
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("chiste", chistePulsado)
        intent.putExtra("categoria", categoria)
        startActivity(intent)
    }
}