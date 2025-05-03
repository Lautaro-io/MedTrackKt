package com.medtrack.view.features.historial

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material3.DropdownMenuItem

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.medtrack.R
import com.medtrack.data.entities.PacienteEntity
import com.medtrack.data.entities.RegistroPacienteEntity
import com.medtrack.ui.theme.OxfordBlue
import com.medtrack.ui.theme.Whitee
import com.medtrack.viewmodel.PacienteViewModel
import com.medtrack.viewmodel.RegistroPacienteViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorialScreen() {

    val viewModelRegistros: RegistroPacienteViewModel = hiltViewModel()
    val viewModelPacientes: PacienteViewModel = hiltViewModel()

    var pacienteSelectedId by remember { mutableStateOf(0) }

    val registerPatient by remember(pacienteSelectedId) {
        viewModelRegistros.getPatientHistory(pacienteSelectedId)
    }.collectAsState(initial = emptyList())

    var expanded by remember { mutableStateOf(false) }
    var options = viewModelPacientes.allPatients.collectAsState(emptyList())
    var selected by remember { mutableStateOf<PacienteEntity?>(null) }


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
                .background(OxfordBlue),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.medtrack),
                contentDescription = "App Logo",
                modifier = Modifier.size(240.dp)
            )


        }
        Spacer(modifier = Modifier.height(20.dp))
        Text("Seleccione un paciente para ver el registro")
        Spacer(modifier = Modifier.height(20.dp))




        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.padding(24.dp)


        ) {
            OutlinedTextField(
                value = selected?.name ?: "Seleccione un paciente",
                onValueChange = {},
                readOnly = true,
                label = { Text("Selecciona un paciente") },

                modifier = Modifier
                    .fillMaxWidth()

                    .menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }


            ) {

                options.value.forEach { paciente ->
                    DropdownMenuItem(
                        onClick = {
                            pacienteSelectedId = paciente.id
                            selected = paciente
                            expanded = false
                        },
                        text = {
                            Text(text = "${paciente.name} ${paciente.surname}")
                        })

                }
            }

        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            if (registerPatient.isNotEmpty()) {

                itemsIndexed(registerPatient) { index, registro ->
                    RegisterItem(registro)
                }
            } else {
                item {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painterResource(R.drawable.caja),
                            contentDescription = "Box Empty",
                            modifier = Modifier.size(120.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("No hay registros para ese paciente")
                    }
                }
            }

        }


    }
}


@Composable
fun RegisterItem(registro: RegistroPacienteEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(OxfordBlue, Whitee),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(modifier = Modifier.padding(24.dp)) {
            Text("Se registro un: ${registro.tipoRegistro} , Descripcion : ${registro.descripcion}")
        }

    }
}