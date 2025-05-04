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
fun DialogBaja(paciente: PacienteEntity, onDismiss: () -> Unit, onConfirmButton : () -> Unit ) {



    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirmButton) {
                Text(
                    "Dar de baja",
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
        title = { Text("Baja paciente") },
        text = { Text("Desea dar de baja a ${paciente.name} ${paciente.surname}?") }

    )

}