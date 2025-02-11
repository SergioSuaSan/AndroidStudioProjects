package net.azarquiel.chistesapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import net.azarquiel.chistesapp.R
import net.azarquiel.chistesapp.adapters.CategoriaAdapter
import net.azarquiel.chistesapp.databinding.ActivityMainBinding
import net.azarquiel.chistesapp.model.Categoria
import net.azarquiel.chistesapp.viewmodel.DataViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: CategoriaAdapter
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolbar)
        initRV()

        val viewModel = ViewModelProvider(this)[DataViewModel::class.java]
        viewModel.getCategorias().observe(this) {
            it?.let {

                    adapter.setCategorias(it)

            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun initRV() {
        adapter = CategoriaAdapter(this, R.layout.rowcategoria)
        binding.cm.rvcategorias.layoutManager = LinearLayoutManager(this)
        binding.cm.rvcategorias.adapter = adapter
    }

    fun onClickCategoria( v: View) {
        val categoriaPulsada = v.tag as Categoria
        val intent = Intent(this, ChistesActivity::class.java)
        intent.putExtra("categoria", categoriaPulsada)
        startActivity(intent)

    }


}