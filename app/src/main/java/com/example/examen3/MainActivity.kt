package com.example.examen3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.examen3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dao = MyApplication.getDatabase(this).usuarioDao()

        binding.btnLogin.setOnClickListener {
            val nombre   = binding.etNombre.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            val usuario = dao.validarCredenciales(nombre, password)
            if (usuario != null) {
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra("NOMBRE", nombre)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnRegister.setOnClickListener {
            val nombre = binding.etNombre.text.toString().trim()

            if (nombre.isEmpty()) {
                startActivity(Intent(this, SignUpActivity::class.java))
                return@setOnClickListener
            }

            val dao = MyApplication.getDatabase(this).usuarioDao()
            if (dao.buscarPorNombre(nombre) != null) {
                Toast.makeText(this, "El usuario ya está registrado", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(Intent(this, SignUpActivity::class.java))
            }
        }
    }
}