package com.example.bsagroupproject.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bsagroupproject.R
import com.example.bsagroupproject.ui.theme.PurpleGrey80
import androidx.compose.material3.DatePicker
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.example.bsagroupproject.data.EventsForm
import com.example.bsagroupproject.model.EventViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventScreen(
    eventViewModel: EventViewModel,
    navHostController: NavHostController
) {
    val pastEvents by eventViewModel.pastEventLiveData.collectAsState()

    val events = listOf(
        "1st April 2024" to "Welcome of Shri Balaji Yatra",
        "1st April 2024" to "General Meet",
        "1st May 2024" to "Family Picnic",
        "1st June 2024" to "Chabeel on Occasion of Nirjala Ekadshi"
    )

    val localContext= LocalContext.current
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

            Row (modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
            ){
                EventItem(pastEvents = "2021", onClick = {
                    eventViewModel.updateSelectedYear("2021")
                    navHostController.navigate("Event_list_Screen/${pastEvents.`2021`}")
                })
                EventItem(pastEvents = "2022", onClick = {
                    eventViewModel.updateSelectedYear("2022")
                    navHostController.navigate("Event_list_Screen/${pastEvents.`2022`}")
                })
                EventItem(pastEvents = "2023", onClick = {
                    eventViewModel.updateSelectedYear("2023")
                    navHostController.navigate("Event_list_Screen/${pastEvents.`2023`}")
                })
                EventItem(pastEvents = "2024", onClick = {
                    eventViewModel.updateSelectedYear("2024")
                    navHostController.navigate("Event_list_Screen/${pastEvents.`2024`}")
                })

            }




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
                EventForm( isFormOpen = isFormOpen,
                    eventViewModel = eventViewModel)
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
fun EventItem(pastEvents: String, onClick: () -> Unit) {
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
                text = pastEvents,
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    }
}


@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EventForm(
    isFormOpen: MutableState<Boolean>,
    eventViewModel: EventViewModel) {
    val yearAndMonth = remember { mutableStateOf("") }
    val eventName = remember { mutableStateOf("") }
    val eventDate = remember { mutableStateOf("") }
    val eventDetails = remember { mutableStateOf("") }
    val eventLocation = remember { mutableStateOf("") }
    val eventLeadMobNo = remember { mutableStateOf("") }
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
        MyEventTextField(labelValue = "Year & Month",textValue=yearAndMonth)
        Spacer(modifier = Modifier.height(16.dp))
        MyEventTextField(labelValue = "Event Name", textValue = eventName)
        Spacer(modifier = Modifier.height(16.dp))
        DatePickerField(label = "Date", selectedDate = eventDate)
        Spacer(modifier = Modifier.height(16.dp))
        MyEventTextField(labelValue = "Event Detail", textValue = eventDetails)
        Spacer(modifier = Modifier.height(16.dp))
        MyEventTextField(labelValue = "Event Location", textValue = eventLocation)
        Spacer(modifier = Modifier.height(16.dp))
        MyEventTextField(labelValue = "Lead's Mobile Number", textValue = eventLeadMobNo)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {

                eventViewModel.registerEventsInfo(
                    EventsForm(
                        yearAndMonth = yearAndMonth.value,
                        eventName = eventName.value,
                        eventDetails = eventDetails.value,
                        eventDate = eventDate.value,
                        eventLeadMobNo = eventLeadMobNo.value,
                        eventLocation = eventLocation.value
                    )
                )
                isFormOpen.value = false
                      },
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
    labelValue: String,
    textValue: MutableState<String>
) {
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

@Composable
fun DatePickerField(
    label: String,
    selectedDate: MutableState<String>
) {
    val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
    var showDialog by remember { mutableStateOf(false) }
    var selectedDateValue by remember { mutableStateOf(Date()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        TextField(
            value = TextFieldValue(text = selectedDate.value),
            onValueChange = {
                selectedDate.value = it.text
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Default),
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .clickable { showDialog = true },
            trailingIcon = {
                IconButton(
                    onClick = { showDialog = true }
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = com.google.android.material.R.drawable.material_ic_calendar_black_24dp),
                        contentDescription = "Calendar",
                    )
                }
            }
        )
        if (showDialog) {
            DatePickerDialog(
                onDismissRequest = { showDialog = false },
                selectedDate = selectedDate,
                dateFormat = dateFormat,
                selectedDateValue = selectedDateValue
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    onDismissRequest: () -> Unit,
    selectedDate: MutableState<String>,
    dateFormat: SimpleDateFormat,
    selectedDateValue: Date
) {
    val datePickerState = rememberDatePickerState()

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties()
    ) {
        Surface(
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                DatePicker(
                    state = datePickerState,
                    title = {
                        Text("Select a date")
                    },
                    showModeToggle = true,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = { onDismissRequest() }) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(
                        onClick = {
                            selectedDate.value = dateFormat.format(
                                Date(
                                    datePickerState.selectedDateMillis ?: selectedDateValue.time
                                )
                            )
                            onDismissRequest()
                        }
                    ) {
                        Text("Ok")
                    }
                }
            }
        }
    }
}
