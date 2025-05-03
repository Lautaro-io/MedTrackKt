package com.medtrack.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import com.medtrack.R
import com.medtrack.view.navigation.MainScreen
import com.medtrack.view.navigation.OnboardingScreen
import kotlinx.coroutines.delay

@Composable
fun Splashscreen(navController: NavController , store : Boolean){

    var screen by remember() { mutableStateOf("") }

    screen = if (store) MainScreen.route else OnboardingScreen.route

    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.navigate(screen){
            popUpTo(0) {inclusive = true}
        }
    }
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.medtrack ), contentDescription = "Logo")
    }
}