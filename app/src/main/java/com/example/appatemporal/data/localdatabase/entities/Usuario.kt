package com.example.appatemporal.data.localdatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.auth.FirebaseUser
import java.util.*

@Entity(tableName = "usuario_table")
data class Usuario(
    @PrimaryKey()
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "apellidos") val apellido: String,
    @ColumnInfo(name = "fechaNacimiento") val fechaNac: Int,
    @ColumnInfo(name = "genero") val genero: String,
    @ColumnInfo(name = "rol") val rol: String,
)
