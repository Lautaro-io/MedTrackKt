package com.medtrack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medtrack.data.entities.RegistroPacienteEntity
import com.medtrack.data.repositories.RegistroPacienteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistroPacienteViewModel @Inject constructor(private val repository: RegistroPacienteRepository) :
    ViewModel() {

    val allHistory: Flow<List<RegistroPacienteEntity>> = repository.getAllhistory()

    fun insertRegister(registro: RegistroPacienteEntity) {
        viewModelScope.launch {
            repository.addRegister(registro)
        }
    }

    fun deleteRegister(registro: RegistroPacienteEntity) {
        viewModelScope.launch {
            repository.deleteRegister(registro)
        }
    }


    fun getPatientHistory(pacienteId: Int): Flow<List<RegistroPacienteEntity>> {
        return repository.getPatientHistory(pacienteId)
    }
}