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
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import java.util.Calendar


class Registrarce : AppCompatActivity() {

    private lateinit var btnRegresar: Button

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
        setContentView(R.layout.activity_registrarce)

        val edtFechaNac: EditText = findViewById(R.id.edt_fecha_nac)

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

        btnRegresar = findViewById(R.id.btn_regresar)

        btnRegresar.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
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

}
