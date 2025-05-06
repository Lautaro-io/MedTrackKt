package com.medtrack.view.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.medtrack.data.dataStore.StoreOnboarding
import com.medtrack.ui.theme.OxfordBlue
import com.medtrack.view.MainScreen
import com.medtrack.view.Splashscreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationWrapperHome() {

    val context = LocalContext.current
    val dataStore = StoreOnboarding(context)
    val store =
        dataStore.getBoarding.collectAsState(false) // inicializamos el onboarding en false para que empiece en falso


    val navController = rememberNavController()
    NavHost(
        navController,
        startDestination = if (store.value) MainScreen.route else Splashscreen.route
    ) {
        composable(OnboardingScreen.route) {
            OnboardingScreen(navController, dataStore)
        }

        composable(MainScreen.route) {
            MainScreen()
        }

        composable(Splashscreen.route) {
            Splashscreen(navController, store.value)
        }

    }
}
