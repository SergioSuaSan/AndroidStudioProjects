package net.azarquiel.chistesapp.view

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import net.azarquiel.chistesapp.databinding.ActivityMainBinding
import net.azarquiel.chistesapp.viewmodel.DataViewModel
import net.azarquiel.chistesapp.adapters.CategoriaAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputLayout
import net.azarquiel.chistesapp.R
import net.azarquiel.chistesapp.model.Categoria
import net.azarquiel.chistesapp.model.Usuario

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var categorias: List<Categoria>
    private var titulo=""
    private lateinit var viewModel: DataViewModel
    private var usuario: Usuario? =null
    private lateinit var adapter: CategoriaAdapter
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        titulo = title.toString()
        initRV()

        viewModel = ViewModelProvider(this)[DataViewModel::class.java]
        viewModel.getCategorias().observe(this) {
            it?.let {
                categorias = it
                adapter.setCategorias(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        // ************* <Filtro> ************
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView
        searchView.setQueryHint("Search...")
        searchView.setOnQueryTextListener(this)
// ************* </Filtro> ************

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_user -> {
                // se pulsó sobre el monigote
                dialogLoginRegister()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun initRV() {
        adapter = CategoriaAdapter(this, R.layout.rowcategoria)
        binding.cm.rvcategorias.layoutManager = LinearLayoutManager(this)
        binding.cm.rvcategorias.adapter = adapter
    }
    fun onClickCategoria(v: View) {
        val categoria = v.tag as Categoria
        val intent = Intent(this, ChistesActivity::class.java)
        intent.putExtra("categoria", categoria)
        startActivity(intent)

    }
    private fun dialogLoginRegister() {
        val builder = AlertDialog.Builder(this)
        val tvTitle = TextView(this)
        tvTitle.text = "Login & Register"
        tvTitle.setPadding(0,30,0,30)
        tvTitle.gravity = Gravity.CENTER
        tvTitle.textSize = 20.0F
        tvTitle.setTextColor(getColor(R.color.azulc))
        tvTitle.setBackgroundColor(getColor(R.color.azulo))
        builder.setCustomTitle(tvTitle)
        val ll = LinearLayout(this)
        ll.setPadding(30,30,30,30)
        ll.orientation = LinearLayout.VERTICAL

        val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        lp.setMargins(0,50,0,50)

        val textInputLayoutNick = TextInputLayout(this)
        textInputLayoutNick.layoutParams = lp
        val etnick = EditText(this)
        etnick.setPadding(0, 80, 0, 80)
        etnick.textSize = 20.0F
        etnick.hint = "Nick"
        textInputLayoutNick.addView(etnick)

        val textInputLayoutPass = TextInputLayout(this)
        textInputLayoutPass.layoutParams = lp
        val etpass = EditText(this)
        etpass.setPadding(0, 80, 0, 80)
        etpass.textSize = 20.0F
        etpass.hint = "Pass"
        textInputLayoutPass.addView(etpass)
        ll.addView(textInputLayoutNick)
        ll.addView(textInputLayoutPass)
        builder.setView(ll)

        builder.setPositiveButton("Aceptar") { dialog, which ->
            login(etnick.text.toString(), etpass.text.toString())
        }

        builder.setNegativeButton("Cancelar") { dialog, which ->
        }

        builder.show()
    }

    private fun login(nick: String, pass: String) {
        viewModel = ViewModelProvider(this)[DataViewModel::class.java]
        viewModel.getDataUsuarioPorNickPass(nick, pass).observe(this) {
            if (it!=null) {
                usuario=it
                title = "$titulo - ${it.nick}"
                Toast.makeText(this, "Login Ok. Bienvenido ${usuario?.nick}", Toast.LENGTH_LONG).show()
            }
            else { //registrar
                viewModel = ViewModelProvider(this)[DataViewModel::class.java]
                viewModel.saveUsuario(Usuario("",nick,pass)).observe(this) {
                    it?.let {
                        usuario = it
                        title = "$titulo - ${it.nick}"
                        Toast.makeText(this, "Registro Ok. Bienvenido ${usuario?.nick}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
    // ************* <Filtro> ************
    override fun onQueryTextChange(query: String): Boolean {
        val original = ArrayList<Categoria>(categorias)
        adapter.setCategorias(original.filter { categoria -> categoria.nombre.contains(query,true) })
        return false
    }

    override fun onQueryTextSubmit(text: String): Boolean {
        return false
    }
// ************* </Filtro> ************



}