package com.example.bsagroupproject.model

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bsagroupproject.LoginUIEvent
import com.example.bsagroupproject.LoginUIState
import com.example.bsagroupproject.Validator
import com.example.bsagroupproject.navigation.Screen
import com.example.bsagroupproject.navigation.ScreenRouting
import com.example.bsagroupproject.screens.HomeScreen
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow

class LoginViewModel : ViewModel() {
    private var  _logInSuccess= MutableStateFlow(false)
    val logInSuccess get() = _logInSuccess

    var loginUIState = mutableStateOf(LoginUIState())

    var allValidationsPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)

    val isUserLoggedIn:MutableLiveData<Boolean> =MutableLiveData()


    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.emailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
            }

            is LoginUIEvent.passwordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
            }

            is LoginUIEvent.LoginButtonClicked -> {
                login()
            }

        }
        validateLoginUIDataWithRules()
    }

    private fun validateLoginUIDataWithRules() {
        val emailResult = Validator.validateEmail(
            email = loginUIState.value.email
        )


        val passwordResult = Validator.validatePassword(
            password = loginUIState.value.password
        )

        loginUIState.value = loginUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status

    }


    private fun login() {
        loginInProgress.value = true
        val email = loginUIState.value.email
        val password = loginUIState.value.password

        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                Log.d("LogIn_Page","LogIn =${it.isSuccessful}")
                if(it.isSuccessful){
                    loginInProgress.value = false
                    _logInSuccess.value=true
                    Log.d("value","${logInSuccess.value}")
                    ScreenRouting.navigateTo(Screen.HomeScreen)
                }

            }
            .addOnFailureListener {
                loginInProgress.value = false
                Log.d("LogIn_Page","Failure: ${it.message}")
            }
    }

//    fun logout() {
//        val firebaseAuth = FirebaseAuth.getInstance()
//        firebaseAuth.signOut()
//
//        val authStateListener = FirebaseAuth.AuthStateListener {
//            if (it.currentUser == null) {
//                Log.d("checkingLogout", "Inside sign Out success")
//                ScreenRouting.navigateTo(Screen.LogInScreen)
//            } else {
//                Log.d("checkingLogout", "Inside sign Out failure")
//            }
//        }
//
//        // Add the AuthStateListener before signing out
//        firebaseAuth.addAuthStateListener(authStateListener)
//    }

//    fun checkForActiveSession() {
//        if (FirebaseAuth.getInstance().currentUser != null) {
//            Log.d("active_session_check", "Valid session")
//            isUserLoggedIn.value = true
//        } else {
//            Log.d("active_session_check", "User is not logged in")
//            isUserLoggedIn.value = false
//        }
//    }

//    val emailId: MutableLiveData<String> = MutableLiveData()
//    fun getUserData() {
//        FirebaseAuth.getInstance().currentUser?.also {
//            it.email?.also { email ->
//                emailId.value = email
//            }
//        }
//
//    }


}