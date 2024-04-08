package com.example.bsagroupproject.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController

sealed class Screen(){
    object SignUpScreen:Screen()
    object LogInScreen:Screen()

}

object ScreenRouting{
    var currentScreen:MutableState<Screen> = mutableStateOf(Screen.SignUpScreen)
    lateinit var navController: NavController

    fun initialize(navController: NavController){
        this.navController=navController
    }
    //navigate to other screen
    fun navigateTo(destination:Screen){
        currentScreen.value = destination


    }
}