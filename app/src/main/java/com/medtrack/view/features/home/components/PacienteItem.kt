package com.medtrack.view.features.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.medtrack.R
import com.medtrack.data.entities.PacienteEntity
import com.medtrack.ui.theme.OxfordBlue
import com.medtrack.ui.theme.Red
import com.medtrack.ui.theme.VistaBlue
import com.medtrack.ui.theme.Whitee
import com.medtrack.utils.GenreType
import com.medtrack.viewmodel.PacienteViewModel


@Composable
fun PacienteItem(paciente: PacienteEntity) {

    val pacienteViewModel: PacienteViewModel = hiltViewModel()

    var expanded by remember { mutableStateOf(false) }
    var showDialogDelete by remember { mutableStateOf(false) }
    var showDialogAlta by remember { mutableStateOf(false) }
    var showDialogBaja by remember { mutableStateOf(false) }


    val genrePaciciente = GenreType.genreToObj(paciente.genreType)
    val genreId = genrePaciciente.image
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            OxfordBlue, Whitee
        ),
        onClick = { expanded = !expanded }


    ) {
        ->
        Row(modifier = Modifier.padding(32.dp)) {
            Image(
                painter = painterResource(genreId),
                contentDescription = "GenrePic",
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.weight(2f))
            Column {

                Text(
                    "Nombre : ${paciente.name} ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = White
                )

                Text(
                    "Apellido : ${paciente.surname}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = White
                )

                Text(
                    "Edad : ${paciente.age}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = White
                )

                Text(
                    "Fecha de ingreso: ${paciente.dateEntry}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold, color = White
                )


            }
            if (paciente.alta) Image(
                painterResource(R.drawable.comprobado),
                contentDescription = "CheckIcon",
                modifier = Modifier.size(32.dp)
            )

        }
        AnimatedVisibility(expanded) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { showDialogDelete = true },
                    modifier = Modifier.padding(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = White,
                        contentColor = OxfordBlue
                    ),
                    border = BorderStroke(2.dp, Red)
                ) { Text("Eliminar paciente") }

                if (!paciente.alta) {
                    Button(
                        onClick = { showDialogAlta = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = White,
                            contentColor = OxfordBlue
                        ),
                        border = BorderStroke(2.dp, VistaBlue)
                    ) { Text("Alta paciente") }
                } else {
                    Button(
                        onClick = { showDialogBaja = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = White,
                            contentColor = OxfordBlue
                        ),
                        border = BorderStroke(2.dp, VistaBlue)
                    ) { Text("Baja paciente") }

                }
            }
        }
        when {
            (showDialogDelete) -> {
                DialogConfirm(
                    paciente,
                    onDismiss = { showDialogDelete = false },
                    onConfirmButton = {
                        pacienteViewModel.deletePatient(paciente)
                        showDialogDelete = false
                    })

            }

            (showDialogAlta) -> {
                DialogAlta(
                    paciente,
                    onDismiss = { showDialogAlta = false },
                    onConfirmButton = {
                        pacienteViewModel.altaPaciente(paciente.id)
                        showDialogAlta = false
                    })
            }

            (showDialogBaja) -> {
                DialogBaja(
                    paciente,
                    onDismiss = { showDialogBaja = false },
                    onConfirmButton = {
                        pacienteViewModel.bajaPaciente(paciente.id)
                        showDialogBaja = false
                    })
            }

        }


    }
}