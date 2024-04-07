package com.example.bsagroupproject.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen(){
    object SignUpScreen:Screen()
    object LogInScreen:Screen()

}

object ScreenRouting{
    var currentScreen:MutableState<Screen> = mutableStateOf(Screen.SignUpScreen)

    //navigate to other screen
    fun navigateTo(destination:Screen){

    }
}