package com.example.bsagroupproject.BsaProjectapp

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bsagroupproject.model.LoginViewModel
import com.example.bsagroupproject.navigation.Screen
import com.example.bsagroupproject.navigation.ScreenRouting
import com.example.bsagroupproject.screens.HomeScreen
import com.example.bsagroupproject.screens.LogInScreen
import com.example.bsagroupproject.screens.SignUpScreen

@Composable
fun BSAapp(loginViewModel: LoginViewModel) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Crossfade(targetState = ScreenRouting.currentScreen, label = "") { currentState ->
            when (currentState.value) {
                is Screen.SignUpScreen -> {
                    SignUpScreen()
                }

                is Screen.LogInScreen -> {
                    LogInScreen(loginViewModel)

                }
            }
        }
    }
}
