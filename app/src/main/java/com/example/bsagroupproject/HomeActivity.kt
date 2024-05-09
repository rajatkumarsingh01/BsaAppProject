package com.example.bsagroupproject

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.bsagroupproject.data.PastEventRequest
import com.example.bsagroupproject.model.ChatViewModel
import com.example.bsagroupproject.model.EventViewModel
import com.example.bsagroupproject.screens.HomeScreen
import com.example.bsagroupproject.ui.theme.BsagroupprojectTheme

class HomeActivity : ComponentActivity() {

    private val chatViewModel by lazy {
        ViewModelProvider(this)[ChatViewModel::class.java]
    }

    private val eventViewModel by lazy {
        ViewModelProvider(this)[EventViewModel::class.java]
    }

    override fun onStart() {
        super.onStart()
        val dummyRequest = PastEventRequest("try", "IN", "read", "Images", "e1")
        val dummyRequest1 = PastEventRequest("try", "IN", "read", "Year", "2024")

        chatViewModel.getPersonList()
        chatViewModel.getUserName()
        eventViewModel.getEventByYearOf("NA")
        eventViewModel.parseEventDetails(requestData =dummyRequest1)
       eventViewModel.getImagesById(dummyRequest)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            BsagroupprojectTheme {
                HomeScreen(chatViewModel)
            }
        }
    }
}
