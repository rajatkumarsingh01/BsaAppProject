package com.example.bsagroupproject.model

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bsagroupproject.ChatOperations
import com.example.bsagroupproject.UIEvent
import com.example.bsagroupproject.Validator
import com.example.bsagroupproject.data.RegistrationUIState
import com.example.bsagroupproject.data.UserData
import com.example.bsagroupproject.navigation.Screen
import com.example.bsagroupproject.navigation.ScreenRouting
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import kotlin.math.log

class   SignupViewModel : ViewModel(){
    //loading data from viewmodel
    var registrationUIState= mutableStateOf(RegistrationUIState())

    var allValidationsPassed = mutableStateOf(false)

    var signUpInProgress = mutableStateOf(false)


    fun onEvent(event:UIEvent){

        when(event){
            is UIEvent.nameChanged -> {
                registrationUIState.value=registrationUIState.value.copy(
                    name = event.name
                )

                printState()
            }
            is UIEvent.emailChanged -> {
                registrationUIState.value=registrationUIState.value.copy(
                    email = event.email
                )

                printState()
            }
            is UIEvent.passwordChanged -> {
                registrationUIState.value=registrationUIState.value.copy(
                    password = event.password
                )

                printState()
            }
            is UIEvent.registrationButton -> {
                   signUp()
                validateDataWithRules()
            }

        }
        validateDataWithRules()
    }

    private fun validateDataWithRules() {
       val nameResult =Validator.validateName(
           name = registrationUIState.value.name
       )

        val emailResult =Validator.validateEmail(
            email = registrationUIState.value.email
        )
        val passwordResult =Validator.validatePassword(
            password =registrationUIState.value.password
        )

        Log.d("validation","Inside_validate_with rules")
        Log.d("validation","name=$nameResult")
        Log.d("validation","name=$emailResult")
        Log.d("validation","name=$passwordResult")

        registrationUIState.value=registrationUIState.value.copy(
            nameError = nameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,

        )

        allValidationsPassed.value = nameResult.status &&
                emailResult.status && passwordResult.status

    }



    private fun signUp() {
        Log.d("simpleName","Inside_SignUp")
        printState()
        createUserInfirebase(
            email = registrationUIState.value.email,
            password = registrationUIState.value.password
        )
    }

    private fun printState(){
        Log.d("simpleName","Inside_printState")
        Log.d("simpleName",registrationUIState.value.toString())

    }

    //settingUp Firebase
    private fun createUserInfirebase(email:String , password:String){
        signUpInProgress.value=true
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                Log.d("Firebase ", "Inside_compltelistner  ")
                Log.d("Firebase ", "is successfull =${it.isSuccessful} ")
                signUpInProgress.value=false

                if (it.isSuccessful){
                    //todo -->navigate to home screen
                    ChatOperations().writeUserData(UserData(userName =registrationUIState.value.name,email=registrationUIState.value.email, userID = it.result.user?.uid
                        ?:" " ))
                    ScreenRouting.navigateTo(Screen.LogInScreen)
                }

            }
            .addOnFailureListener {
                Log.d("Firebase ", "Inside_Failure ")
                Log.d("Firebase ", "Excepyion =${it.message}")
                Log.d("Firebase ", "Exception =${it.localizedMessage}")

            }

    }
}