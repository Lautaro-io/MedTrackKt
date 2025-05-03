package com.medtrack.data.repositories

import com.medtrack.data.dao.RegistroPacienteDao
import com.medtrack.data.entities.RegistroPacienteEntity
import javax.inject.Inject


class RegistroPacienteRepository @Inject constructor(private val registroDao: RegistroPacienteDao) {


    suspend fun addRegister(registro: RegistroPacienteEntity) =
        registroDao.agregarRegistro(registro)

    suspend fun deleteRegister(registro: RegistroPacienteEntity) =
        registroDao.borrarRegistro(registro)

    fun getAllhistory() = registroDao.getAllHistories()

    fun getPatientHistory(pacienteId: Int) = registroDao.getPatientHistory(pacienteId)
}