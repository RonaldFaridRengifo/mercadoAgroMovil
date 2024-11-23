package fup.edu.pruebamercadoagro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btnRegistrarse: Button
    private lateinit var btnIniciarSesion: Button
    private lateinit var btnVerProductos: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnRegistrarse = findViewById(R.id.btn_registrarse)
        btnIniciarSesion = findViewById(R.id.btn_ingresar)
        btnVerProductos = findViewById(R.id.btn_verProductos)

        btnRegistrarse.setOnClickListener{
            val intent = Intent(this, Registrarce   ::class.java)
            startActivity(intent)
        }

        btnIniciarSesion.setOnClickListener{
            val intent = Intent(this, IniciarSesion   ::class.java)
            startActivity(intent)
        }

        btnVerProductos.setOnClickListener{
            val intent = Intent(this, ProductosSinSesion   ::class.java)
            startActivity(intent)
        }
    }
}