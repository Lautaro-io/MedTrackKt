package com.medtrack.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
 import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.medtrack.view.features.historial.HistorialScreen
import com.medtrack.view.features.home.HomeScreen
import com.medtrack.view.features.register.RegisterPatientScreen
import com.medtrack.view.features.register.RegisterScreen
import com.medtrack.view.navigation.RegisterScreen
import com.medtrack.view.navigation.bottomnav.bottomnavscreens.Historial
import com.medtrack.view.navigation.bottomnav.bottomnavscreens.Home
import com.medtrack.view.navigation.bottomnav.bottomnavscreens.Register

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable

fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
        BottomNavigation(
            backgroundColor = White,
            contentColor = contentColorFor(backgroundColor = Black)
        ) {
            val currentRoute =
                navController.currentBackStackEntryAsState().value?.destination?.route // aca convierte la pila de pantallas que visito en un estado para que compose lo entienda y lo pueda recomponer

            BottomNavigationItem(
                selected = currentRoute == "home",
                onClick = { navController.navigate(Home.route){launchSingleTop = true} },
                icon = { Icon(Icons.Default.Home, contentDescription ="home icon" ) },
                label = { Text("Home") }
            )

            BottomNavigationItem(
                selected = currentRoute == "register",
                onClick = { navController.navigate(Register.route){launchSingleTop = true} },
                icon = { Icon(Icons.Default.Person, contentDescription = "Person Icon") },
                label = { Text("Paciente") }
            )

            BottomNavigationItem(
                selected = currentRoute == "registros",
                onClick = { navController.navigate(RegisterScreen.route){launchSingleTop = true} },
                icon = { Icon(Icons.Default.Info, contentDescription = "Historial Icon") },
                label = { Text("Registros") }
            )
            BottomNavigationItem(
                selected = currentRoute == "historial",
                onClick = { navController.navigate(Historial.route){launchSingleTop = true} },
                icon = { Icon(Icons.Default.DateRange, contentDescription = "Historial Icon") },
                label = { Text("Historial") }
            )


        }
    }) { innerPadding ->
        NavHost(navController, startDestination = Home.route , modifier = Modifier.padding(innerPadding)){
            composable (Home.route){
                HomeScreen()
            }
            composable (Register.route){
                RegisterPatientScreen()
            }
            composable (RegisterScreen.route){
                RegisterScreen()
            }
            composable (Historial.route){
                HistorialScreen()
            }

        }
    }


}
