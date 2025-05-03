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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.Color.Companion.White

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.medtrack.R
import com.medtrack.data.entities.PacienteEntity
import com.medtrack.ui.theme.DarkImperialBlue
import com.medtrack.ui.theme.OxfordBlue
import com.medtrack.ui.theme.myFont
import com.medtrack.viewmodel.PacienteViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterPatientScreen() {

    val context = LocalContext.current
    val viewModel: PacienteViewModel = hiltViewModel()

    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    var selectedGenre by remember { mutableStateOf("") }

    //Columna General
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
        Text(
            "Registrar nuevo paciente",
            fontSize = 24.sp,
            fontFamily = myFont
        )

        //Columna contenedora de los TextField
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            // TextField del Nombre
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )

            // TextField del Apellido
            OutlinedTextField(
                value = surname,
                onValueChange = { surname = it },
                label = { Text("Apellido") },
                modifier = Modifier.fillMaxWidth()
            )

            // TextField de la Edad
            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                label = { Text("Edad") },
                modifier = Modifier.fillMaxWidth()
            )
            //
            Text("Selecciona un genero")
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedGenre == "masculino",
                    onClick = { selectedGenre = "masculino" }
                )
                Text("Masculino")


                RadioButton(
                    selected = selectedGenre == "femenino",
                    onClick = { selectedGenre = "femenino" })
                Text("Femenino")
            }


            //Button para agregar el paciente a la db
            Button(
                onClick = {
                    when {
                        listOf(name, surname, age).any { it.isEmpty() } -> Toast.makeText(
                            context,
                            "Completa todos los campos",
                            Toast.LENGTH_SHORT
                        ).show()

                        else -> {
                            viewModel.addPatient(
                                PacienteEntity(
                                    0,
                                    name,
                                    surname,
                                    age.toInt(),
                                    LocalDate.now().format(formatter),
                                    selectedGenre
                                )
                            )
                            Toast.makeText(
                                context,
                                "Paciente agregado con exito!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }


                    }


                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = White,
                    contentColor = OxfordBlue
                ),
                border = BorderStroke(
                    2.dp,
                    DarkImperialBlue
                )
            ) {
                Text(
                    "Registrar paciente",
                    fontWeight = FontWeight.Bold,
                    color = OxfordBlue,
                    fontSize = 24.sp
                )
            }


        }

    }
}