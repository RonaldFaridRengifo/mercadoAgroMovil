package fup.edu.pruebamercadoagro

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.text.TextUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar


class ConfigurarPerfil : AppCompatActivity() {

    private lateinit var btnCancelar: Button
    private lateinit var edtNombre: EditText
    private lateinit var edtApellido: EditText
    private lateinit var edtCorreo: EditText
    private lateinit var edtFechaNac: EditText
    private lateinit var edtTelefono: EditText
    private lateinit var spUbicacion: Spinner
    private lateinit var edtUsuario: EditText
    private lateinit var btnGuardar: Button

    private lateinit var imageButton: ImageButton
    private var imageUri: Uri? = null

    // Crear un launcher para abrir el selector de imágenes
    private val selectImageLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            // Obtener la URI de la imagen seleccionada
            imageUri = result.data?.data
            // Mostrar la imagen seleccionada en el ImageButton
            imageButton.setImageURI(imageUri)
        } else {
            Toast.makeText(this, "Imagen no seleccionada", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configurar_perfil)

        edtFechaNac = findViewById(R.id.edt_fecha_nac)
        edtNombre = findViewById(R.id.edt_nombre)
        edtApellido = findViewById(R.id.edt_apellido)
        edtCorreo = findViewById(R.id.edt_correo)
        edtTelefono = findViewById(R.id.edt_telefono)
        spUbicacion = findViewById(R.id.sp_ubicacion)
        edtUsuario = findViewById(R.id.edt_usuario)
        btnGuardar = findViewById(R.id.btn_guardar)
        btnCancelar = findViewById(R.id.btn_cancelar)

        // Listener del botón Registrarse
        btnGuardar.setOnClickListener {
            if (validarCampos()) {
                // Aquí puedes continuar con la lógica para registrar al usuario
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imageButton = findViewById(R.id.img_selecionada)

        // Configurar el evento de clic para el ImageButton
        imageButton.setOnClickListener {
            openImageSelector()
        }

        btnCancelar.setOnClickListener{
            val intent = Intent(this, ProductosSinSesion::class.java)
            startActivity(intent)
        }

        val spinnerUbicacion: Spinner = findViewById(R.id.sp_ubicacion)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.ubicaciones_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerUbicacion.adapter = adapter

        edtFechaNac.setOnClickListener {
            val calendario = Calendar.getInstance()
            val anio = calendario.get(Calendar.YEAR)
            val mes = calendario.get(Calendar.MONTH)
            val dia = calendario.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    edtFechaNac.setText("$dayOfMonth/${month + 1}/$year")
                }, anio, mes, dia
            )
            datePickerDialog.show()
        }
    }

    private fun openImageSelector() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        selectImageLauncher.launch(intent)
    }

    private fun validarCampos(): Boolean {
        // Validar que ningún campo esté vacío
        if (TextUtils.isEmpty(edtNombre.text.toString())) {
            edtNombre.error = "Ingrese su nombre"
            return false
        }

        if (TextUtils.isEmpty(edtApellido.text.toString())) {
            edtApellido.error = "Ingrese su apellido"
            return false
        }

        if (TextUtils.isEmpty(edtCorreo.text.toString())) {
            edtCorreo.error = "Ingrese su correo"
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(edtCorreo.text.toString()).matches()) {
            edtCorreo.error = "Ingrese un correo válido"
            return false
        }

        if (TextUtils.isEmpty(edtTelefono.text.toString())) {
            edtTelefono.error = "Ingrese su teléfono"
            return false
        }

        if (TextUtils.isEmpty(edtFechaNac.text.toString())) {
            edtFechaNac.error = "Seleccione su fecha de nacimiento"
            return false
        } else {
            // Limpiar el error si la fecha ya fue seleccionada
            edtFechaNac.error = null
        }

        if (spUbicacion.selectedItem.toString() == "Seleccionar ") {
            Toast.makeText(this, "Seleccione su ubicación", Toast.LENGTH_SHORT).show()
            return false
        }


        if (TextUtils.isEmpty(edtUsuario.text.toString())) {
            edtUsuario.error = "Ingrese su número de identificación"
            return false
        }

        return true
    }

}
