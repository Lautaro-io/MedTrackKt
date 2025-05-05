package com.medtrack.view.features.register.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.medtrack.data.entities.PacienteEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenuPatients(options : List<PacienteEntity>, selected : PacienteEntity?, onPacienteSelected : (PacienteEntity) -> Unit){
    var expanded by remember{ mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth().padding(24.dp)
    ) {
        //Usamos un textfield para manejar los valores
        OutlinedTextField(
            value = selected?.name ?: "Seleccione un paciente",
            onValueChange = {}, //selected?.name si no hay ninguno seleccionado se pone un valor x defecto
            readOnly = true, //unicamente leible, no se modifica
            label = { Text("Selecciona una opcion") },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
            ,
            leadingIcon = {Icon(Icons.Default.ArrowDropDown, contentDescription = "Icon")}

        )

        ExposedDropdownMenu( //Este contiene las opciones desplegables
            expanded = expanded,
            onDismissRequest = { expanded = false }


        ) {


            options.forEach { paciente -> //por cada paciente , se crea un item del dropdown manejando el click y dandole el texto
                DropdownMenuItem(
                    onClick = {
                        onPacienteSelected(paciente)
                        expanded = false
                    },
                    text = {
                        Text(text = "${paciente.name} ${paciente.surname}")
                    })

            }

        }
    }
}
