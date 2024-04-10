package com.example.bsagroupproject.screens

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bsagroupproject.LoginUIEvent
import com.example.bsagroupproject.R
import com.example.bsagroupproject.components.ButtonComponent
import com.example.bsagroupproject.components.ClickableLoginTextComponent
import com.example.bsagroupproject.components.DividerTextComponents
import com.example.bsagroupproject.components.MyPasswordField
import com.example.bsagroupproject.components.MyTextField
import com.example.bsagroupproject.components.NormalText
import com.example.bsagroupproject.components.NormalTextHeading
import com.example.bsagroupproject.components.UnderLinedText
import com.example.bsagroupproject.model.LoginViewModel
import com.example.bsagroupproject.navigation.Screen
import com.example.bsagroupproject.navigation.ScreenRouting
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LogInScreen(loginViewModel: LoginViewModel){


    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){

        Surface(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
        ) {
            val context= LocalContext.current
            Column {
                NormalText(value = stringResource(id = R.string.hello))
                NormalTextHeading(value = stringResource(id = R.string.welcome))
                Spacer(modifier = Modifier.height(60.dp))

                MyTextField(
                    labelValue = stringResource(id = R.string.email),
                    imageVector =Icons.Default.Email,
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.emailChanged(it))
                    },
                    errorStatus =  loginViewModel.loginUIState.value.emailError
                )



                MyPasswordField(
                    labelValue = stringResource(id = R.string.password),
                    imageVector = Icons.Default.Lock,
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.passwordChanged(it))
                    },
                    errorStatus =  loginViewModel.loginUIState.value.passwordError
                )

                Spacer(modifier = Modifier.height(12.dp))
                UnderLinedText(value = stringResource(id = R.string.forgot_password))
                Spacer(modifier = Modifier.height(150.dp))
                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {
                        loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)

                    },
                    isEnabled = loginViewModel.allValidationsPassed.value
                )
                Spacer(modifier = Modifier.height(8.dp))
                DividerTextComponents()

                ClickableLoginTextComponent(tryingToLogin = false,onTextSelected ={
                    ScreenRouting.navigateTo(Screen.SignUpScreen)
                } )
            }
        }
        if (loginViewModel.loginInProgress.value){
            CircularProgressIndicator()
        }
        BackHandler {
            ScreenRouting.navigateTo(Screen.SignUpScreen)
        }
    }

}

