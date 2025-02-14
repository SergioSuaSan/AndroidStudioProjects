package net.azarquiel.chistesapp.view

import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import net.azarquiel.chistesapp.R
import net.azarquiel.chistesapp.model.Categoria
import net.azarquiel.chistesapp.model.Chiste
import net.azarquiel.chistesapp.model.Punto
import net.azarquiel.chistesapp.viewmodel.DataViewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var rbavgdetail: RatingBar
    private lateinit var rbdetail: RatingBar
    private lateinit var tvcategoriadetail: TextView
    private lateinit var tvchistedetail: TextView
    private lateinit var ivcategoriadetail: ImageView
    private lateinit var categoria: Categoria
    private lateinit var chiste: Chiste

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        chiste = intent.getSerializableExtra("chiste") as Chiste
        categoria = intent.getSerializableExtra("categoria") as Categoria
        findView()
        getAvg()
        pintaDatos()

    }

    private fun getAvg() {
        val viewModel = ViewModelProvider(this)[DataViewModel::class.java]
        viewModel.getAvgChiste(chiste.id).observe(this) {
            it?.let {
                rbavgdetail.rating = it.toFloat()
            }
        }
    }

    private fun pintaDatos() {
        Picasso.get().load("http://www.ies-azarquiel.es/paco/apichistes/img/${categoria.id}.png").into(ivcategoriadetail)
        tvcategoriadetail.text = categoria.nombre
        tvchistedetail.text = Html.fromHtml(chiste.contenido)
    }

    private fun findView() {
        ivcategoriadetail = findViewById<ImageView>(R.id.ivcategoriadetail)
        tvcategoriadetail = findViewById<TextView>(R.id.tvcategoriadetail)
        tvchistedetail = findViewById<TextView>(R.id.tvchistedetail)
        rbavgdetail = findViewById<RatingBar>(R.id.rbavgdetail)
        rbdetail = findViewById<RatingBar>(R.id.rbdetail)
        rbdetail.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            puntuar(rating)
        }
    }

    private fun puntuar(rating: Float) {
        val punto = Punto("", chiste.id, rating.toString())
        val viewModel = ViewModelProvider(this)[DataViewModel::class.java]
        viewModel.savePuntos(chiste.id, punto).observe(this) {
            it?.let {
                Toast.makeText(this, "Rating ok...", Toast.LENGTH_SHORT).show()
                getAvg()
            }
        }
    }

}