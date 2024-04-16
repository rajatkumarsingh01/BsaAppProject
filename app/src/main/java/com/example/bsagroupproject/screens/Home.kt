package com.example.bsagroupproject.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bsagroupproject.LoginUIEvent
import com.example.bsagroupproject.R
import com.example.bsagroupproject.components.ButtonComponent
import com.example.bsagroupproject.model.LoginViewModel
import com.example.bsagroupproject.navigation.Screen
import com.example.bsagroupproject.navigation.ScreenRouting

@Composable
fun Home(){


    Column(modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Welcome to Home Screen ")
        Spacer(modifier = Modifier.height(40.dp))
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Image(painter = painterResource(id = R.drawable.icon_instagram), contentDescription ="insta" )
            Spacer(modifier = Modifier.width(14.dp))
            Image(painter = painterResource(id = R.drawable.icon_facebook), contentDescription ="facebook" )
        }

    }
}