package com.medtrack.view.features.home.components


import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.medtrack.viewmodel.PacienteViewModel


@Preview(showBackground = true , showSystemUi = true)
@Composable
fun SearchPatient() {
    val pacienteViewModel : PacienteViewModel = hiltViewModel()

    var name by remember { mutableStateOf("") }
    val results by pacienteViewModel.searchPatients(name).collectAsState(emptyList())
    OutlinedTextField(
        value = name,
        onValueChange = { name = it },
        label = { Text("Buscar paciente") })
}