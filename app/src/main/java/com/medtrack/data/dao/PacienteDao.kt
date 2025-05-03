package com.medtrack.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.medtrack.data.entities.PacienteEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface PacienteDao {


    @Insert
    suspend fun agregarPaciente(paciente: PacienteEntity)

    @Delete
    suspend fun borrarPaciente(paciente : PacienteEntity)


    @Query ("SELECT * FROM pacientes")
    fun getAllPatient() : Flow<List<PacienteEntity>>

}