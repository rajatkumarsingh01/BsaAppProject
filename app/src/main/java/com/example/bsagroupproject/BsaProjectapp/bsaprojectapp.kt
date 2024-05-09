package com.example.bsagroupproject.BsaProjectapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import com.example.bsagroupproject.model.ChatViewModel
import com.example.bsagroupproject.model.LoginViewModel
import com.example.bsagroupproject.navigation.Screen
import com.example.bsagroupproject.navigation.ScreenRouting
import com.example.bsagroupproject.screens.HomeScreen
import com.example.bsagroupproject.screens.LogInScreen
import com.example.bsagroupproject.screens.SignUpScreen

@RequiresApi(Build.VERSION_CODES.O)
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

                is Screen.HomeScreen ->{

                    HomeScreen(chatViewModel = ChatViewModel())
                }
            }
        }
    }
}
