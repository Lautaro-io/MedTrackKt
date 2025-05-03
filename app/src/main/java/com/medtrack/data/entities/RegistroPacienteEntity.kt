package com.medtrack.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "registros_paciente",
    foreignKeys = [
        ForeignKey(
            PacienteEntity::class,
            parentColumns = ["id"],
            childColumns = ["paciente_id"],
            onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("paciente_id")]
)

data class RegistroPacienteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val paciente_id: Int,
    val registerDate: String,
    val tipoRegistro: String,
    val descripcion: String,
)