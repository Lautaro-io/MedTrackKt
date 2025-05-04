package com.medtrack.data.repositories

import com.medtrack.data.dao.PacienteDao
import com.medtrack.data.entities.PacienteEntity
import javax.inject.Inject


class PacienteRepository @Inject constructor(private val pacienteDao: PacienteDao){

    suspend fun addPatient(paciente: PacienteEntity) = pacienteDao.agregarPaciente(paciente)

    suspend fun deletePatient(paciente: PacienteEntity) = pacienteDao.borrarPaciente(paciente)

    fun getAllPatients() = pacienteDao.getAllPatient()

    suspend fun altaPaciente(pacienteID : Int ) = pacienteDao.altaPaciente(pacienteID , true)

    suspend fun bajaPaciente(pacienteID : Int ) = pacienteDao.altaPaciente(pacienteID , false)


}