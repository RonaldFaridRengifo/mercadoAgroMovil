package fup.edu.pruebamercadoagro

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductosSinSesion : AppCompatActivity() {

    private lateinit var rvProductos : RecyclerView
    private lateinit var btnHome: ImageButton
    private lateinit var btnProductos: ImageButton
    private lateinit var btnSubirProducto: ImageButton
    private lateinit var btnConfigurarPerfil: ImageButton
    private lateinit var btnInisiarSesion: ImageButton
    private lateinit var btnCerrarSesion: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_productos_sin_sesion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvProductos = findViewById(R.id.rv_productos)
        btnSubirProducto = findViewById(R.id.btn_agregar_producto)
        btnConfigurarPerfil = findViewById(R.id.btn_config)
        btnHome = findViewById(R.id.btn_home)
        btnProductos = findViewById(R.id.btn_productos)
        btnInisiarSesion = findViewById(R.id.btn_iniciar_sesion)
        btnCerrarSesion = findViewById(R.id.btn_cerrar_sesion)

        val listadoUsuarios = listOf(
            ItemProducto(1, 1, "Fup", "Hola", 1,1,
                "","","Lulo","Nose",1000,"",
                R.drawable.red_huertas_logo,"Libra","Fresco",1,1,1),
            ItemProducto(1, 1, "Fup2", "Hola2", 1,1,
                "","","Papaya","Nose",2000,"",
                R.drawable.perfil,"Kilo","Fresco2",1,1,1),
            ItemProducto(1, 1, "Fup3", "Hola3", 1,1,
                ""+R.drawable.red_huertas_logo,"","Dulce","Nose",3000,"",
                R.drawable.red_huertas_logo,"Unidad","Transformado",1,1,1),
         )

        rvProductos.layoutManager = LinearLayoutManager(this)
        rvProductos.adapter = ProductosAdapter(listadoUsuarios)

        btnSubirProducto.setOnClickListener{
            val intent = Intent(this, SubirProductos ::class.java)
            startActivity(intent)
        }

        btnConfigurarPerfil.setOnClickListener{
            val intent = Intent(this, ConfigurarPerfil ::class.java)
            startActivity(intent)
        }

        btnProductos.setOnClickListener{
            val intent = Intent(this, ProductosSinSesion ::class.java)
            startActivity(intent)
        }

        btnHome.setOnClickListener{
            val intent = Intent(this, Home ::class.java)
            startActivity(intent)
        }

        btnInisiarSesion.setOnClickListener{
            val intent = Intent(this, IniciarSesion ::class.java)
            startActivity(intent)
        }

        btnCerrarSesion.setOnClickListener{
            val builder = AlertDialog.Builder(this)
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