package com.medtrack.view.features.register

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown

import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.medtrack.R
import com.medtrack.data.entities.PacienteEntity
import com.medtrack.data.entities.RegistroPacienteEntity
import com.medtrack.ui.theme.OxfordBlue
import com.medtrack.view.navigation.RegisterScreen
import com.medtrack.viewmodel.PacienteViewModel
import com.medtrack.viewmodel.RegistroPacienteViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen() {
    //contexto
    val context = LocalContext.current
    //viewmodels
    val viewModelPacientes: PacienteViewModel = hiltViewModel()
    val viewModelRegistros: RegistroPacienteViewModel = hiltViewModel()

    //radio buttons
    var selectedType by remember { mutableStateOf("") }
    //dropdown menu
    var expanded by remember { mutableStateOf(false) }
    var options = viewModelPacientes.allPatients.collectAsState(emptyList())
    var selected by remember { mutableStateOf<PacienteEntity?>(null) }

    //description
    var description by remember { mutableStateOf("") }
    //date formater
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")


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
        Text("Seleccione el tipo de registro")
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            RadioButton(
                selected = selectedType == "medicamento",
                onClick = { selectedType = "medicamento" }
            )
            Text("Medicamento")
            RadioButton(
                selected = selectedType == "sintoma",
                onClick = { selectedType = "sintoma" }
            )
            Text("Sintoma")

            Spacer(modifier = Modifier.height(16.dp))

        }

        //Creamos el dropdownmenu

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


                options.value.forEach { paciente -> //por cada paciente , se crea un item del dropdown manejando el click y dandole el texto
                    DropdownMenuItem(
                        onClick = {
                            selected = paciente
                            expanded = false
                        },
                        text = {
                            Text(text = "${paciente.name} ${paciente.surname}")
                        })

                }

            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripcion") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        )
        Button(
            onClick = {
                when {
                    listOf(selectedType, selected, description).any { it.toString().isEmpty() } ->
                        Toast.makeText(context, "Completa todos los campos", Toast.LENGTH_SHORT)
                            .show()

                    else -> {
                        viewModelRegistros.insertRegister(
                            RegistroPacienteEntity(
                                0, selected?.id ?: 0, LocalDate.now().format(formatter),
                                tipoRegistro = selectedType,
                                descripcion = description
                            )
                        )
                        Toast.makeText(context, "Registro agregado con exito!", Toast.LENGTH_SHORT)
                            .show()
                    }

                }

            },
            colors = ButtonDefaults.buttonColors(containerColor = White, contentColor = OxfordBlue),
            border = BorderStroke(2.dp, OxfordBlue),
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) { Text("Registrar", fontSize = 24.sp, fontWeight = FontWeight.Bold) }
    }
}