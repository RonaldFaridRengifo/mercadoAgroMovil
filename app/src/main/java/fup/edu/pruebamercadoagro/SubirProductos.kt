package fup.edu.pruebamercadoagro

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class SubirProductos : AppCompatActivity() {

    private lateinit var imageButton: ImageButton
    private lateinit var edtNombreProd: EditText
    private lateinit var spUnidadVenta: Spinner
    private lateinit var spTipoVenta: Spinner
    private lateinit var edtPrecio: EditText
    private lateinit var edtDescripcion: EditText
    private lateinit var spCategoria: Spinner
    private lateinit var btnGuardar: Button
    private lateinit var btnCancelar: Button

    private var imageUri: Uri? = null

    private val selectImageLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            imageUri = result.data?.data
            imageButton.setImageURI(imageUri)
        } else {
            Toast.makeText(this, "Imagen no seleccionada", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subir_productos)

        imageButton = findViewById(R.id.img_producto)
        edtNombreProd = findViewById(R.id.edt_nombre_prod)
        spUnidadVenta = findViewById(R.id.sp_unidadVenta)
        spTipoVenta = findViewById(R.id.sp_tipoVenta)
        edtPrecio = findViewById(R.id.edt_precio)
        edtDescripcion = findViewById(R.id.edt_descripcion)
        spCategoria = findViewById(R.id.sp_categoria)
        btnGuardar = findViewById(R.id.btn_guardar)
        btnCancelar = findViewById(R.id.btn_cancelar)

        imageButton.setOnClickListener { openImageSelector() }

        btnGuardar.setOnClickListener {
            if (validarCampos()) {
                Toast.makeText(this, "Producto registrado correctamente", Toast.LENGTH_SHORT).show()
            }
        }

        btnCancelar.setOnClickListener {
            startActivity(Intent(this, ProductosSinSesion::class.java))
        }

        configurarSpinners()
    }

    private fun openImageSelector() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        selectImageLauncher.launch(intent)
    }

    private fun validarCampos(): Boolean {

        if (imageUri == null) {
            Toast.makeText(this, "Seleccione una imagen del producto", Toast.LENGTH_SHORT).show()
            return false
        }

        if (TextUtils.isEmpty(edtNombreProd.text.toString())) {
            edtNombreProd.error = "Ingrese el nombre del producto"
            return false
        }

        if (spUnidadVenta.selectedItem.toString() == "Seleccionar") {
            Toast.makeText(this, "Seleccione una unidad de venta", Toast.LENGTH_SHORT).show()
            return false
        }

        if (spTipoVenta.selectedItem.toString() == "Seleccionar") {
            Toast.makeText(this, "Seleccione un tipo de venta", Toast.LENGTH_SHORT).show()
            return false
        }

        if (TextUtils.isEmpty(edtPrecio.text.toString())) {
            edtPrecio.error = "Ingrese el precio del producto"
            return false
        }

        if (TextUtils.isEmpty(edtDescripcion.text.toString())) {
            edtDescripcion.error = "Ingrese una descripción del producto"
            return false
        }

        if (spCategoria.selectedItem.toString() == "Seleccionar") {
            Toast.makeText(this, "Seleccione una categoría", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun configurarSpinners() {

        val spinnerUnidad: Spinner = findViewById(R.id.sp_unidadVenta)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.unidad_venta_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerUnidad.adapter = adapter

        val spinnerTipo: Spinner = findViewById(R.id.sp_tipoVenta)
        val adapter1 = ArrayAdapter.createFromResource(
            this,
            R.array.tipo_venta_array,
            android.R.layout.simple_spinner_item
        )
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // Ajustado
        spinnerTipo.adapter = adapter1

        val spinnerCategoria: Spinner = findViewById(R.id.sp_categoria)
        val adapter2 = ArrayAdapter.createFromResource(
            this,
            R.array.categoria_array,
            android.R.layout.simple_spinner_item
        )
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // Ajustado
        spinnerCategoria.adapter = adapter2

    }

}
