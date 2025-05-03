package com.medtrack.view.navigation

import android.util.Log
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.medtrack.R
import com.medtrack.data.dataStore.StoreOnboarding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun OnboardingScreen(navController: NavController, store: StoreOnboarding) {


    val pages = listOf("Bienvenido!", "Registra Pacientes!", "Controla su historial!")
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { pages.size })


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState, modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { page ->
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {


                Image(
                    painter = painterResource(R.drawable.medtrack),
                    contentDescription = "Logo"
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(pages[page], fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(24.dp))
                Row {
                    repeat(pages.size) {
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(
                                    if (pagerState.currentPage == it) {
                                        Color.Blue
                                    } else {
                                        Color.Blue.copy(alpha = 0.35f)
                                    }
                                )
                        )

                    }
                }
            }

        }


        if (pagerState.currentPage == pages.size - 1) {
            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        store.saveBoarding(true)
                    }
                    navController.navigate("mainscreen")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF020F59)
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 6.dp,
                    disabledElevation = 0.dp
                ),
                border = ButtonDefaults.outlinedButtonBorder(true),

                modifier = Modifier
                    .padding(32.dp)
                    .fillMaxWidth()
            ) {
                Text("Empezar ", fontWeight = FontWeight.Bold, fontSize = 24.sp)
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Arrow left")
            }
        }


    }


}