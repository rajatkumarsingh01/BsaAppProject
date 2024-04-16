package com.example.bsagroupproject.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bsagroupproject.R
import com.example.bsagroupproject.data.PastEvent

@Composable
fun EventScreen() {
    val events = listOf(
        "1st April 2024" to "Welcome of Shri Balaji Yatra",
        "1st April 2024" to "General Meet",
        "1st May 2024" to "Family Picnic",
        "1st June 2024" to "Chabeel on Occasion of Nirjala Ekadshi"
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 40.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                text = "Upcoming Events",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )
            UpcomingEvents(events = events, onEventClick = { event ->
                // Handle event click here
            })
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Past Events",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )
            PastEvents()
        }

        FloatingActionButton(
            onClick = {
                // Handle FAB click here
            },
            modifier = Modifier
                .padding(bottom = 40.dp, end = 32.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add Event",
                tint = Color.Black
            )
        }
    }
}

@Composable
fun EventBanner(date: String, event: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(10.dp)
            .heightIn(min = 80.dp)
            .background(color = Color(0xFFFFB2D0), RoundedCornerShape(8.dp))

    ){
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .background(color=Color(0xFF006400)),
                text = date,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = event,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
        }
    }

}

@Composable
fun UpcomingEvents(events: List<Pair<String, String>>, onEventClick: (Pair<String, String>) -> Unit) {
    LazyRow {
        items(events) { event ->
            EventBanner(date = event.first, event = event.second, onClick = { onEventClick(event) })
        }
    }
}


@Composable
fun PastEvents() {
    LazyRow(modifier = Modifier
        .background(color = Color(0xFFFFB38A))
    ) {
        items(pastEvents) { event ->
            EventItem(event)
        }
    }
}

@Composable
fun EventItem(pastevent: PastEvent) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(8.dp) // Adding a rounded corner shape
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = pastevent.title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(id = R.drawable.calendar_svgrepo_com),
                contentDescription = "Calendar Image",
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = pastevent.year,
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    }
}

val pastEvents = listOf(
    PastEvent(
        title = "Past Event 1",
        year= "2023"
    ),
    PastEvent(
        title = "Past Event 2",
        year = "2022"
    ),
    PastEvent(
        title = "Past Event 2",
        year = "2021"
    ),
    PastEvent(
        title = "Past Event 2",
        year = "2020"
    ),
    PastEvent(
        title = "Past Event 2",
        year = "2019"
    )
)


@Preview
@Composable
private fun PreviewEventScreen(){
    EventScreen()

}