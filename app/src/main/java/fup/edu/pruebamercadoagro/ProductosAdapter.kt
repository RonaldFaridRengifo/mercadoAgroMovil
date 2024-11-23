package fup.edu.pruebamercadoagro

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductosAdapter(private val listadoProductos: List<ItemProducto>) :
    RecyclerView.Adapter<ProductosAdapter.MyHolder>() {

    //MyHolder contiene a las vistas
    class MyHolder(item: View) : RecyclerView.ViewHolder(item) {
        val imagen: ImageView = item.findViewById(R.id.imagen)
        val txtTitulo: TextView = item.findViewById(R.id.txt_titulo)
        val txtUnidad: TextView = item.findViewById(R.id.txt_unidad)
        val txtPrecio: TextView = item.findViewById(R.id.txt_precio)
        val txtUbicacion: TextView = item.findViewById(R.id.txt_ubicacion)
    }

    //Inflar el diseño
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_producto,
            parent, false
        )
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {


        val item = listadoProductos[position]
        holder.imagen.setImageResource(item.pro_image)
        holder.txtTitulo.text = item.pro_name
        holder.txtUnidad.text = item.pro_unit
        holder.txtPrecio.text = item.pro_price.toString()
        holder.txtUbicacion.text = item.pro_type
    }

    override fun getItemCount(): Int {
        return listadoProductos.size
    }
}