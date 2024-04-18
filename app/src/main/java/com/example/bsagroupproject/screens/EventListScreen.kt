package com.example.bsagroupproject.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bsagroupproject.data.PastEventResponse
import com.example.bsagroupproject.model.EventViewModel
import com.example.bsagroupproject.data.CreateEventResponse

@Composable
fun EventListScreen(events: List<PastEventResponse>, eventViewModel: EventViewModel, createEventResponses: List<CreateEventResponse>) {
    val expandedStates = remember { mutableStateListOf<Boolean>() }
    for (i in events.indices) {
        expandedStates.add(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp, start = 32.dp, end = 32.dp, bottom = 40.dp)
    ) {
        events.forEachIndexed { index, event ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(if (expandedStates[index]) 60.dp else 16.dp)
                    .background(Color(0xFFFFA500))  // Orange color
                    .padding(8.dp)
                    .clickable { expandedStates[index] = !expandedStates[index] }
            ) {
                if (expandedStates[index]) {
                    val eventData = createEventResponses[index]
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "Event Date: ${eventData.eventDate}",
                            fontSize = 16.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(4.dp)
                        )
                        Text(
                            text = "Event Location: ${eventData.eventLocation}",
                            fontSize = 16.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(4.dp)
                        )
                        Text(
                            text = "Lead By Mobile: ${eventData.leadByMob}",
                            fontSize = 16.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(4.dp)
                        )
                        // Add event image here
                    }
                } else {
                    Text(
                        text = "${event.year}: ${event.eventName}",
                        fontSize = 20.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}
