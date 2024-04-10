package com.example.bsagroupproject.data

data class RegistrationUIState(
    //hOld data in this Ui state

    var name:String="",
    var email:String="",
    var password:String="",


    var nameError:Boolean=false ,
    var emailError:Boolean=false ,
    var passwordError:Boolean=false


)
