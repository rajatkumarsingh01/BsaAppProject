package com.example.bsagroupproject.data

data class PastEventRequest(
    val uID: String,
    val country: String,
    val action: String,
    val queryType: String,
    val queryValue: String
)
data class PastEventResponse(
    val `2021`: String="",
    val `2022`: String="",
    val `2023`: String="",
    val `2024`: String=""
)

data class EventDetails(
    val `2024`: Map<String, EventItem> = emptyMap(),
    val `2023`: Map<String, EventItem> = emptyMap(),
    val `2022`: Map<String, EventItem> = emptyMap(),
    val `2021`: Map<String, EventItem> = emptyMap()
)

data class EventItem(
    val eventDT: String,
    val eventDetail: String,
    val eventID: String,
    val eventLocation: String,
    val eventMonth: String,
    val eventName: String,
    val leadByMob: String,
    val year: String
)


data class EventResponseForImages(
    val push1: EventItemForImages,
    val push2: EventItemForImages,
    val push3: EventItemForImages,
    val push4: EventItemForImages
)
data class EventItemForImages(
    val eventID: String="",
    val eventImagePath: String=""
)
