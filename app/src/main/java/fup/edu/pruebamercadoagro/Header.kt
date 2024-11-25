package fup.edu.pruebamercadoagro

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout

class Header @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.activity_header, this)
        setupListeners()
    }

    private fun setupListeners() {
        val btnSubirProducto = findViewById<ImageButton>(R.id.btn_agregar_producto)
        val btnConfigurarPerfil = findViewById<ImageButton>(R.id.btn_config)
        val btnHome = findViewById<ImageButton>(R.id.btn_home)
        val btnProductos = findViewById<ImageButton>(R.id.btn_productos)
        val btnIniciarSesion = findViewById<ImageButton>(R.id.btn_iniciar_sesion)
        val btnCerrarSesion = findViewById<ImageButton>(R.id.btn_cerrar_sesion)

        btnSubirProducto.setOnClickListener {
            context.startActivity(Intent(context, SubirProductos::class.java))
        }

        btnConfigurarPerfil.setOnClickListener {
            context.startActivity(Intent(context, ConfigurarPerfil::class.java))
        }

        btnProductos.setOnClickListener {
            context.startActivity(Intent(context, ProductosSinSesion::class.java))
        }

        btnHome.setOnClickListener {
            context.startActivity(Intent(context, Home::class.java))
        }

        btnIniciarSesion.setOnClickListener {
            context.startActivity(Intent(context, IniciarSesion::class.java))
        }

        btnCerrarSesion.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Cerrar sesión")
            builder.setMessage("¿Estás seguro de que deseas cerrar sesión?")
            builder.setPositiveButton("Sí") { dialog, _ ->
                dialog.dismiss() // Cierra el diálogo
            }
            builder.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss() // Simplemente cierra el diálogo
            }
            builder.create().show() // Muestra el cuadro de diálogo
        }
    }
}
