package com.medtrack.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.medtrack.data.entities.RegistroPacienteEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface RegistroPacienteDao {


    @Insert
    suspend fun agregarRegistro(Registro: RegistroPacienteEntity)

    @Delete
    suspend fun borrarRegistro(Registro: RegistroPacienteEntity)

    @Query( " SELECT * FROM registros_paciente")
    fun getAllHistories() : Flow<List<RegistroPacienteEntity>>


    @Query ( "SELECT * FROM registros_paciente WHERE paciente_id =:paciente_id")
    fun getPatientHistory(paciente_id : Int) : Flow<List<RegistroPacienteEntity>>
}