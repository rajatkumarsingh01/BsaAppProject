package com.example.bsagroupproject.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController
import com.example.bsagroupproject.model.LoginViewModel

sealed class Screen(){
    object SignUpScreen:Screen()
    object LogInScreen:Screen()
    object HomeScreen:Screen()

}

object ScreenRouting{

    var currentScreen:MutableState<Screen> = mutableStateOf(Screen.LogInScreen)

    //navigate to other screen
    fun navigateTo(destination:Screen){
        currentScreen.value = destination
    }


}
