package com.example.bsagroupproject.data

data class Message(
    val content: String = "",
    val senderUID: String = "",
    val receiverUID: String = "",
    val direction: Boolean = false,
)


