package com.example.bsagroupproject

sealed class LoginUIEvent{

    data class emailChanged(val email:String): LoginUIEvent()
    data class passwordChanged(val password: String) : LoginUIEvent()

    object LoginButtonClicked : LoginUIEvent()
}