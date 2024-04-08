package com.example.bsagroupproject.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bsagroupproject.R
import com.example.bsagroupproject.components.ButtonComponent
import com.example.bsagroupproject.components.ClickableLoginTextComponent
import com.example.bsagroupproject.components.DividerTextComponents
import com.example.bsagroupproject.components.MyPasswordField
import com.example.bsagroupproject.components.MyTextField
import com.example.bsagroupproject.components.NormalText
import com.example.bsagroupproject.components.NormalTextHeading
import com.example.bsagroupproject.components.UnderLinedText
import com.example.bsagroupproject.navigation.Screen
import com.example.bsagroupproject.navigation.ScreenRouting

@Composable
fun LogInScreen(){
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(28.dp)
    ) {
        Column {
           NormalText(value = stringResource(id = R.string.hello))
            NormalTextHeading(value = stringResource(id = R.string.welcome))
            Spacer(modifier = Modifier.height(60.dp))

            MyTextField(
                labelValue = stringResource(id = R.string.email),
                imageVector =Icons.Default.Email )

            MyPasswordField(
                labelValue = stringResource(id = R.string.password),
                imageVector = Icons.Default.Lock
            )

            Spacer(modifier = Modifier.height(12.dp))
            UnderLinedText(value = stringResource(id = R.string.forgot_password))
            Spacer(modifier = Modifier.height(150.dp))
            ButtonComponent(value = stringResource(id = R.string.login))
            Spacer(modifier = Modifier.height(8.dp))
            DividerTextComponents()

            ClickableLoginTextComponent(tryingToLogin = false,onTextSelected ={
                ScreenRouting.navigateTo(Screen.SignUpScreen)
            } )
        }
    }
    BackHandler {
        ScreenRouting.navigateTo(Screen.SignUpScreen)
    }
}

@Preview
@Composable
private fun PreviewLogInScreen() {
    LogInScreen()

}