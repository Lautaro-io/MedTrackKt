package com.medtrack.view.features.home.components

import android.widget.Toast
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.medtrack.data.entities.PacienteEntity
import com.medtrack.ui.theme.OxfordBlue
import com.medtrack.ui.theme.Red
import com.medtrack.viewmodel.PacienteViewModel

@Composable
fun DialogAlta(paciente: PacienteEntity, onDismiss: () -> Unit , onConfirmButton : () -> Unit ) {
    val context = LocalContext.current
    val pacienteViewModel: PacienteViewModel = hiltViewModel()
    if (paciente.alta) {
        onDismiss
        Toast.makeText(context, "El paciente ya esta dado de alta", Toast.LENGTH_SHORT).show()
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirmButton) {
                Text(
                    "Dar de alta",
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
        title = { Text("Alta paciente") },
        text = { Text("Desea dar de alta a ${paciente.name} ${paciente.surname}?") }

    )
}