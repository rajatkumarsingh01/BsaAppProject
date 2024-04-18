package com.example.bsagroupproject.data

data class PastEventRequest(
    val uID: String,
    val country: String,
    val action: String,
    val queryType: String,
    val queryValue: String
)
data class PastEventResponse(
    val year: String,
    val eventName: String
)

data class CreateEventRequest(
    val uID: String,
    val country: String,
    val action: String,
    val year: String,
    val eventName: String,
    val eventDate: String,
    val eventDetail: String,
    val eventLocation: String,
    val eventMonth: String,
    val leadByMob: String
)



data class CreateEventResponse(
    val eventName: String,
    val eventDate: String,
    val eventID: String,
    val eventLocation: String,
    val eventMonth: String,
    val leadByMob: String,
    val eventDetail:String

)
