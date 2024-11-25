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


    }
}