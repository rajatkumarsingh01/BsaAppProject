package com.example.bsagroupproject.BsaProjectapp

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.bsagroupproject.data.CreateEventResponse
import com.example.bsagroupproject.model.ChatViewModel
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

                is Screen.HomeScreen ->{
                    val createEventResponses = listOf( // Sample data for createEventResponses
                        CreateEventResponse(
                            eventName = "Event A",
                            eventDate = "06.01.2022",
                            eventID = "1",
                            eventLocation = "Hotel Canopy Green, Dehradun",
                            eventMonth = "January",
                            leadByMob = "9068224365",
                            eventDetail = "Event details for Event A"
                        ),
                        CreateEventResponse(
                            eventName = "Event B",
                            eventDate = "05.01.2022",
                            eventID = "2",
                            eventLocation = "Hotel ABC, Dehradun",
                            eventMonth = "January",
                            leadByMob = "9068224366",
                            eventDetail = "Event details for Event B"
                        )
                    )
                    HomeScreen(chatViewModel = ChatViewModel(), createEventResponse =createEventResponses)
                }
            }
        }
    }
}
