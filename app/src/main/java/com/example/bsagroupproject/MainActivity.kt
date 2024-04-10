package com.example.bsagroupproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bsagroupproject.BsaProjectapp.BSAapp
import com.example.bsagroupproject.model.LoginViewModel
import com.example.bsagroupproject.ui.theme.BsagroupprojectTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    private val loginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        setContent {
            val logInSuccess by loginViewModel.logInSuccess.collectAsState()
            if(logInSuccess){
                Log.d("staringActivity ","inside main activity  ")
                startActivity(Intent(this,HomeActivity::class.java))
                finish()
            }
            BSAapp(loginViewModel)

        }
    }
}
