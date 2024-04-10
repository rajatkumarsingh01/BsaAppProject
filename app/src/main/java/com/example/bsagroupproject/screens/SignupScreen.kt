package com.example.bsagroupproject.screens

import android.content.Intent
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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bsagroupproject.R
import com.example.bsagroupproject.UIEvent
import com.example.bsagroupproject.components.ButtonComponent
import com.example.bsagroupproject.components.ClickableLoginTextComponent
import com.example.bsagroupproject.components.DividerTextComponents
import com.example.bsagroupproject.components.MyPasswordField
import com.example.bsagroupproject.components.MyTextField
import com.example.bsagroupproject.components.NormalText
import com.example.bsagroupproject.components.NormalTextHeading
import com.example.bsagroupproject.components.checkBoxComponent
import com.example.bsagroupproject.model.SignupViewModel
import com.example.bsagroupproject.navigation.Screen
import com.example.bsagroupproject.navigation.ScreenRouting

@Composable
fun SignUpScreen(signUpViewModel: SignupViewModel = viewModel()){
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)

        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                val context= LocalContext.current
                NormalText(value = stringResource(id = R.string.hello))
                NormalTextHeading(value = stringResource(id =R.string.create_an_account))
                Spacer(modifier = Modifier.height(60.dp))

                MyTextField(labelValue = stringResource(id = R.string.user_name),
                    imageVector = Icons.Default.Person,
                    onTextSelected = {
                        //the value will be updated inside Ui state
                        signUpViewModel.onEvent(UIEvent.nameChanged(it))
                    },
                    errorStatus =  signUpViewModel.registrationUIState.value.nameError
                )
                MyTextField(
                    labelValue = stringResource(id = R.string.email),
                    imageVector = Icons.Default.Email,
                    onTextSelected = {
                        signUpViewModel.onEvent(UIEvent.emailChanged(it))
                    },
                    errorStatus =  signUpViewModel.registrationUIState.value.emailError
                )
                MyPasswordField(
                    labelValue = stringResource(id = R.string.password),
                    imageVector = Icons.Default.Lock,
                    onTextSelected = {
                        signUpViewModel.onEvent(UIEvent.passwordChanged(it))
                    },
                    errorStatus =    signUpViewModel.registrationUIState.value.passwordError
                )
                checkBoxComponent(value = stringResource(id = R.string.check_box))

                Spacer(modifier = Modifier.height(60.dp))

                ButtonComponent(value= stringResource(id = R.string.register), onButtonClicked = {
                    signUpViewModel.onEvent(UIEvent.registrationButton)
                },
                    isEnabled = signUpViewModel.allValidationsPassed.value
                )
                Spacer(modifier = Modifier.height(20.dp))
                DividerTextComponents()

                ClickableLoginTextComponent(tryingToLogin = true,onTextSelected ={
                    ScreenRouting.navigateTo(Screen.LogInScreen)
                } )
            }

        }
        if (signUpViewModel.signUpInProgress.value){
            CircularProgressIndicator()
        }

    }

}

@Preview
@Composable
 fun PreviewSignUpScreen() {
    SignUpScreen()

}

