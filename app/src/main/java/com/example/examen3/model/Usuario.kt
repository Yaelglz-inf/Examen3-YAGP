package com.example.examen3.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity("usuarios_table")
data class Usuario(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val password: String,
    val ultimaConexion: Date? = null
)