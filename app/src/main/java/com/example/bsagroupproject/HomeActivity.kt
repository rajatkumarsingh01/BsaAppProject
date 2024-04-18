package com.example.bsagroupproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.bsagroupproject.data.CreateEventRequest
import com.example.bsagroupproject.data.CreateEventResponse
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
        chatViewModel.getPersonList()
        chatViewModel.getUserName()
        eventViewModel.getEventByYear("2024")
        eventViewModel.createEvent(eventData = CreateEventRequest("89op","Jan","read","2024","Holi","24/01/2024","This is done.","samastipur","January","7004173227"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val createEventResponses = listOf(
            CreateEventResponse(
                eventName = "Event A",
                eventDate = "06.01.2022",
                eventID = "1",
                eventLocation = "Hotel Canopy Green, Dehradun",
                eventMonth = "January",
                leadByMob = "9068224365",
                eventDetail = "Event details for Event A"
            ),
            CreateEventResponse(
                eventName = "Event B",
                eventDate = "05.01.2022",
                eventID = "2",
                eventLocation = "Hotel ABC, Dehradun",
                eventMonth = "January",
                leadByMob = "9068224366",
                eventDetail = "Event details for Event B"
            )
        )

        setContent {
            BsagroupprojectTheme {
                HomeScreen(chatViewModel, createEventResponses)
            }
        }
    }
}
