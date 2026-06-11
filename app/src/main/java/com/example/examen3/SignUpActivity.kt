package com.example.examen3

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.examen3.databinding.ActivitySignUpBinding
import com.example.examen3.model.Usuario

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dao = MyApplication.getDatabase(this).usuarioDao()

        binding.btnSignUp.setOnClickListener {
            val nombre   = binding.etNombre.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (nombre.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Ejercicio 8: validar si ya existe
            if (dao.buscarPorNombre(nombre) != null) {
                Toast.makeText(this, "El usuario ya está registrado", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val resultado = dao.insertar(Usuario(nombre = nombre, password = password))
            if (resultado != -1L) {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}