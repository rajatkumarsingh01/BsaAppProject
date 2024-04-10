package com.example.bsagroupproject

sealed class UIEvent {
    //user perform action
    data class nameChanged(val name:String):UIEvent()
    data class emailChanged(val email:String):UIEvent()
    data class passwordChanged(val password:String):UIEvent()

    object  registrationButton:UIEvent()

}