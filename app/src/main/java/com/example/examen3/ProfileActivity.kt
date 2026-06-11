package com.example.examen3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.examen3.databinding.ActivityProfileBinding
import java.text.SimpleDateFormat
import java.util.*

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dao    = MyApplication.getDatabase(this).usuarioDao()
        val nombre = intent.getStringExtra("NOMBRE") ?: return

        // Mostrar la última conexión anterior
        val usuario = dao.buscarPorNombre(nombre)
        binding.tvLastConnection.text = if (usuario?.ultimaConexion != null) {
            val formato = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
            "Última conexión: ${formato.format(usuario.ultimaConexion)}"
        } else {
            "Primera vez que inicias sesión"
        }

        // Guardar la conexión actual
        usuario?.let {
            dao.actualizar(it.copy(ultimaConexion = Date()))
        }
    }
}