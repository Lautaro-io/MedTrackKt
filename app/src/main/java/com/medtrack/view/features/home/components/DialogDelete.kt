package com.medtrack.view.features.home.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.medtrack.data.entities.PacienteEntity
import com.medtrack.ui.theme.OxfordBlue
import com.medtrack.ui.theme.Red
import com.medtrack.viewmodel.PacienteViewModel


@Composable
fun DialogConfirm(paciente: PacienteEntity, onDismiss: () -> Unit, onConfirmButton : () -> Unit ) {

    val pacienteViewModel: PacienteViewModel = hiltViewModel()


    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirmButton) {
                Text(
                    "Eliminar",
                    color = Red
                )
            }
        },


        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    "Cancelar",
                    color = OxfordBlue
                )
            }
        },
        title = { Text("Eliminar paciente") },
        text = { Text("Desea eliminar a ${paciente.name} ${paciente.surname}?") }

    )

}