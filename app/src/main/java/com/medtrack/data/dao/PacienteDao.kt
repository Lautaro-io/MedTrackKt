package com.medtrack.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
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

    @Query ("UPDATE pacientes SET alta = :newAlta WHERE id = :pacienteID ")
    suspend fun altaPaciente(pacienteID : Int ,newAlta : Boolean)

    @Query ("UPDATE pacientes SET alta = :newAlta WHERE id = :pacienteID ")
    suspend fun bajaPaciente(pacienteID : Int ,newAlta : Boolean)

    @Query("SELECT * FROM PACIENTES WHERE name LIKE '%' || :pacienteName || '%' ")
    fun searchPatients(pacienteName : String): Flow<List<PacienteEntity>>

}