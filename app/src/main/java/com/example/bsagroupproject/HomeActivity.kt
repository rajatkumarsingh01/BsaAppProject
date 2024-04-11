package com.example.bsagroupproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.bsagroupproject.model.ChatViewModel
import com.example.bsagroupproject.model.LoginViewModel
import com.example.bsagroupproject.screens.HomeScreen
import com.example.bsagroupproject.ui.theme.BsagroupprojectTheme

class HomeActivity : ComponentActivity() {

    private val chatViewModel by lazy {
        ViewModelProvider(this)[ChatViewModel::class.java]
    }

    override fun onStart() {
        super.onStart()
        chatViewModel.getPersonList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BsagroupprojectTheme {
                HomeScreen(chatViewModel)
                //   Text(text = "this is Home screen ")
            }
        }
    }
}
