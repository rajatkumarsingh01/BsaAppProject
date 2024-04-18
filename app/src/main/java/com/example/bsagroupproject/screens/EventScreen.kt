package com.example.bsagroupproject.screens

import android.app.DatePickerDialog
import android.text.format.DateUtils
import android.widget.DatePicker
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bsagroupproject.R
import com.example.bsagroupproject.ui.theme.PurpleGrey80
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDefaults.dateFormatter
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.key.Key.Companion.Calendar
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.bsagroupproject.data.PastEventRequest
import com.example.bsagroupproject.data.PastEventResponse
import com.example.bsagroupproject.model.EventViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.*
import kotlin.time.Duration.Companion.days


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventScreen(eventViewModel: EventViewModel,  navHostController: NavHostController) {
    val events = listOf(
        "1st April 2024" to "Welcome of Shri Balaji Yatra",
        "1st April 2024" to "General Meet",
        "1st May 2024" to "Family Picnic",
        "1st June 2024" to "Chabeel on Occasion of Nirjala Ekadshi"
    )
//check for form
    val isFormOpen = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
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
            PastEvents( navHostController)
        }

        FloatingActionButton(
            onClick = {
                isFormOpen.value = true
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
        if (isFormOpen.value) {
            ModalBottomSheet(
                onDismissRequest = { isFormOpen.value = false },
                sheetState = sheetState

            ) {
                EventForm(isFormOpen = isFormOpen)
            }
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

    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .background(color = Color(0xFF006400)),
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
fun UpcomingEvents(
    events: List<Pair<String, String>>,
    onEventClick: (Pair<String, String>) -> Unit
) {
    LazyRow {
        items(events) { event ->
            EventBanner(date = event.first, event = event.second, onClick = { onEventClick(event) })
        }
    }
}


@Composable
fun PastEvents(  navHostController: NavHostController) {
    LazyRow(
        modifier = Modifier
            .background(color = Color(0xFFFFB38A))
    ) {
        items(pastEvents) { event ->
            EventItem(pastEvents=event,  onClick = {  navHostController.navigate("Event_list_Screen/${event.year}") } )
        }
    }
}

@Composable
fun EventItem(pastEvents:PastEventResponse, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(8.dp) // Adding a rounded corner shape
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(id = R.drawable.calendar_svgrepo_com),
                contentDescription = "Calendar Image",
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = pastEvents.year,
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    }
}

val pastEvents = listOf(
    PastEventResponse(
        eventName = "BSA launch Program",
        year = "2023",
    ),
    PastEventResponse(
        eventName = "Holi drive",
        year = "2022"
    ),
    PastEventResponse(
        eventName = "Meeting",
        year = "2021",
    ),
    PastEventResponse(
        eventName = "Vaishakhi",
        year = "2025"
    ),
)

@Composable
fun EventForm(isFormOpen: MutableState<Boolean>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier,
            style = TextStyle(
                fontSize = 24.sp
            ),
            textAlign = TextAlign.Center, text = "Create Event"
        )
        Spacer(modifier = Modifier.height(16.dp))
        MyEventTextField(labelValue = "Year & Month")
        Spacer(modifier = Modifier.height(16.dp))
        MyEventTextField(labelValue = "Event Name")
        Spacer(modifier = Modifier.height(16.dp))
        DateTextField(
            value = "",
            onValueChange = {},
            label = "dd-mm-yyyy",
            imeAction = ImeAction.Next,
        )
        Spacer(modifier = Modifier.height(16.dp))
        MyEventTextField(labelValue = "Event Detail")
        Spacer(modifier = Modifier.height(16.dp))
        MyEventTextField(labelValue = "Event Location")
        Spacer(modifier = Modifier.height(16.dp))
        MyEventTextField(labelValue = "Lead's Mobile Number")
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { isFormOpen.value = false },
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        ) {
            Text(
                text = "Submit",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyEventTextField(
    labelValue: String
) {

    val textValue = remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            ),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = PurpleGrey80,
            unfocusedBorderColor = PurpleGrey80,
            cursorColor = PurpleGrey80

        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Default),
        singleLine = false,
        maxLines = 5,
        value = textValue.value,
        onValueChange = { newValue ->
            textValue.value = newValue
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    imeAction: ImeAction,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true
) {
    val state = rememberDatePickerState()
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
            }
        ) {
            TextField(
                value = state.selectedDateMillis.toString(),
                onValueChange = { onValueChange(it) },
                label = { Text(label) },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = imeAction),
                singleLine = singleLine,
                visualTransformation = VisualTransformation.None,
                readOnly = true
            )
            val showDatePicker = remember {
                mutableStateOf(false)
            }
            IconButton(
                onClick = {
                    showDatePicker.value = !showDatePicker.value
                }
            ) {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = "Select date"
                )
            }

            if (showDatePicker.value)
                DatePickerDialog(
                    onDismissRequest = { showDatePicker.value = !showDatePicker.value },
                    confirmButton = {
                        OutlinedButton(onClick = { showDatePicker.value = !showDatePicker.value }) {
                            Text(text = "Save")
                        }
                    }) {
                    DatePicker(state = state, dateFormatter = dateFormatter(), headline = {
                        DatePickerDefaults.DatePickerHeadline(
                            selectedDateMillis = state.selectedDateMillis,
                            displayMode = state.displayMode,
                            dateFormatter = dateFormatter()
                        )
                    }

                    )

                }
        }
    }
}

@Preview
@Composable
private fun PreviewDateTextField() {
    DateTextField(
        value = "",
        onValueChange = {},
        label = "Date",
        imeAction = ImeAction.Done
    )
}

