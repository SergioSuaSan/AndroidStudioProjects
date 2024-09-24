package net.azarquiel.myapplicationalskdjfhlaksdjhfalj

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var lhbotones: LinearLayout
    private lateinit var lhbotones2: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lhbotones = findViewById(R.id.Columna)
        for (i in 0 until lhbotones.childCount) {
            lhbotones2 = lhbotones.getChildAt(i) as LinearLayout
            for (j in 0 until lhbotones2.childCount) {
                if (lhbotones2.getChildAt(j) is Button) {
                    val boton = lhbotones2.getChildAt(j) as Button
                    boton.setOnClickListener { v -> onButtonClick(v) }
                }

            }

        }
    }

    private fun onButtonClick(v: View?) {
        val botonPulsado = v as Button
        val tag = botonPulsado.tag.toString().toInt()
        when (tag) {
            1 -> {
                Toast.makeText(this, "Botón 1 pulsado", Toast.LENGTH_SHORT).show()
            }
            3 -> {
                Toast.makeText(this, "Botón 2 pulsado", Toast.LENGTH_SHORT).show()
            }
            4 -> {
                Toast.makeText(this, "Botón 3 pulsado", Toast.LENGTH_SHORT).show()
            }
            6 -> {
                Toast.makeText(this, "Botón 4 pulsado", Toast.LENGTH_SHORT).show()
            }
            7 -> {
                Toast.makeText(this, "Botón 5 pulsado", Toast.LENGTH_SHORT).show()
            }
            9 -> {
                Toast.makeText(this, "Botón 6 pulsado", Toast.LENGTH_SHORT).show()
            }
        }
    }


}