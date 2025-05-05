package com.medtrack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medtrack.data.entities.PacienteEntity
import com.medtrack.data.repositories.PacienteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class PacienteViewModel @Inject constructor(private val repository: PacienteRepository) : ViewModel(){

    val allPatients: Flow<List<PacienteEntity>> = repository.getAllPatients()


    fun addPatient(paciente: PacienteEntity){
        viewModelScope.launch{
                repository.addPatient(paciente)
        }
    }

    fun deletePatient(paciente : PacienteEntity) {
        viewModelScope.launch {
            repository.deletePatient(paciente)
        }
    }

    fun altaPaciente(pacienteID : Int){
        viewModelScope.launch {
            repository.altaPaciente(pacienteID)
        }
    }

    fun bajaPaciente(pacienteID : Int){
        viewModelScope.launch {
            repository.bajaPaciente(pacienteID)
        }
    }

     fun searchPatients(pacienteName: String): Flow<List<PacienteEntity>> {
        return repository.searchPatient(pacienteName)
    }





}