package net.azarquiel.openweather

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.openweather.adapter.CustomAdapter
import net.azarquiel.openweather.model.Resultado
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        initRV()
        getDatos()

    }
    private fun initRV() {
        val rvPrincipal = findViewById<RecyclerView>(R.id.rvPrincipal)
        adapter = CustomAdapter(this, R.layout.row_weather)
        rvPrincipal.adapter = adapter
        rvPrincipal.layoutManager = LinearLayoutManager(this)


    }
    private fun getDatos() {
        GlobalScope.launch() {
            val jsontxt = URL("https://api.openweathermap.org/data/2.5/forecast?q=Toledo,es&APPID=601c9db344b44f9774ef76a4c07979b1&lang=sp&units=metric").readText(Charsets.UTF_8)
            launch(Dispatchers.Main) {
                val result = Gson().fromJson(jsontxt, Resultado::class.java)

                adapter.setResultados(result.dias)


            }
        }


    }


}