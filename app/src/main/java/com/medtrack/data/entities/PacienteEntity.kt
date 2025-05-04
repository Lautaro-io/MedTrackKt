package com.medtrack.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity( tableName = "pacientes")
data class PacienteEntity(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val name : String,  // nombre
    val surname : String, // Apellido
    val age : Int, // Edad
    val dateEntry : String,
    val genreType: String,
    var alta : Boolean = false// Fecha de ingreso
)
