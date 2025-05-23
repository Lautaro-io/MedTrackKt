package com.medtrack.view.features.home

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.medtrack.R
import com.medtrack.data.entities.PacienteEntity
import com.medtrack.utils.GenreType
import com.medtrack.ui.theme.OxfordBlue
import com.medtrack.ui.theme.RichBlack
import com.medtrack.ui.theme.Whitee
import com.medtrack.ui.theme.myFont
import com.medtrack.view.features.home.components.PacienteItem
import com.medtrack.viewmodel.PacienteViewModel


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreen() {

    val pacientesViewmodel: PacienteViewModel = hiltViewModel()
    var nameSearch by remember { mutableStateOf("") }
    val pacientes by if (nameSearch.isEmpty()) {
        pacientesViewmodel.allPatients.collectAsState(initial = emptyList())
    } else {
        pacientesViewmodel.searchPatients(nameSearch).collectAsState(initial = emptyList())
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                //.height(250.dp)
                .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
                .background(OxfordBlue),
            contentAlignment = Alignment.Center


        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(R.drawable.medtrack),
                    contentDescription = "Icon",
                    modifier = Modifier.size(240.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "Bienvenido",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = White,
                    fontFamily = myFont,
                    modifier = Modifier.padding(bottom = 15.dp),
                    textAlign = TextAlign.Center
                )
            }

        }
        Text(
            "Pacientes",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = RichBlack,
            fontFamily = myFont,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
        OutlinedTextField(
            value = nameSearch,
            onValueChange = { nameSearch = it },
            label = { Text("Buscar pacientes") },
            leadingIcon = { Icon(Icons.Default.Search , contentDescription = "IconSearch")}
            ,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp , vertical = 4.dp)
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            if (pacientes.isNotEmpty()) {
                itemsIndexed(pacientes.reversed()) { index, item ->
                    PacienteItem(item)
                }
            } else {
                item {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Image(
                            painterResource(R.drawable.caja),
                            contentDescription = "Imagen",
                            modifier = Modifier.size(120.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "No hay pacientes registrados aun",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = RichBlack.copy(alpha = 0.8f)
                        )

                    }
                }
            }


        }
    }


}

