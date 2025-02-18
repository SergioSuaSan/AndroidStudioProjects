package net.azarquiel.chistesapp.view

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import net.azarquiel.chistesapp.R
import net.azarquiel.chistesapp.adapters.ChisteAdapter
import net.azarquiel.chistesapp.model.Categoria
import net.azarquiel.chistesapp.model.Chiste
import net.azarquiel.chistesapp.viewmodel.DataViewModel

class ChistesActivity : AppCompatActivity() {
    private lateinit var chistes: List<Chiste>
    private lateinit var viewModel: DataViewModel
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

        viewModel = ViewModelProvider(this)[DataViewModel::class.java]
        viewModel.getChistesByCategoria(categoria.id).observe(this) {
            it?.let {
                chistes = it
                adapter.setChistes(it)
            }
        }

    }
    private fun dialogNewChiste() {
        val builder = AlertDialog.Builder(this)
        val tvTitle = TextView(this)
        tvTitle.text = "New Chiste"
        tvTitle.setPadding(0,30,0,30)
        tvTitle.gravity = Gravity.CENTER
        tvTitle.textSize = 20.0F
        tvTitle.setTextColor(getColor(R.color.azulo))
        tvTitle.setBackgroundColor(getColor(R.color.azulc))
        builder.setCustomTitle(tvTitle)
        val ll = LinearLayout(this)
        ll.setPadding(30,30,30,30)
        ll.orientation = LinearLayout.VERTICAL

        val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        lp.setMargins(0,50,0,50)

        val textInputLayoutChiste = TextInputLayout(this)
        textInputLayoutChiste.layoutParams = lp
        val etchiste = EditText(this)
        etchiste.setSingleLine(false)
        etchiste.setPadding(0, 80, 0, 80)
        etchiste.textSize = 20.0F
        etchiste.hint = "Content chiste"
        textInputLayoutChiste.addView(etchiste)

        ll.addView(textInputLayoutChiste)
        builder.setView(ll)

        builder.setPositiveButton("Aceptar") { dialog, which ->
            if (etchiste.text.toString().isNotEmpty()) {
                newChiste(etchiste.text.toString())
            }
        }

        builder.setNegativeButton("Cancelar") { dialog, which ->
        }

        builder.show()
    }

    private fun newChiste(chisteContent: String) {
        val viewModel = ViewModelProvider(this)[DataViewModel::class.java]
        viewModel.saveChiste(Chiste("", categoria.id, chisteContent)).observe(this) {
            it?.let {
                Toast.makeText(this, "Chiste guardado", Toast.LENGTH_LONG).show()
                val original = ArrayList<Chiste>(chistes)
                original.add(0, it)
                chistes = original
                adapter.setChistes(chistes)
            }
        }
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