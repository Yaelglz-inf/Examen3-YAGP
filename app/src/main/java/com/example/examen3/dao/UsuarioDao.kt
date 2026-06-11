package com.example.examen3.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.examen3.model.Usuario

//me equivoque e hice esto antes de hacer el commit anterior por eso ahora en el commit d ela actividad 4 no va a cambiar esta parte profesor
@Dao
interface UsuarioDao {

    @Insert
    fun insertar(usuario: Usuario): Long

    @Query("SELECT * FROM usuarios_table WHERE nombre = :nombre LIMIT 1")
    fun buscarPorNombre(nombre: String): Usuario?

    @Query("SELECT * FROM usuarios_table WHERE nombre = :nombre AND password = :password LIMIT 1")
    fun validarCredenciales(nombre: String, password: String): Usuario?

    @Update
    fun actualizar(usuario: Usuario)
}