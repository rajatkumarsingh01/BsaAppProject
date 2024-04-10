package com.example.bsagroupproject.model


import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.bsagroupproject.ChatOperations
import com.example.bsagroupproject.data.Message
import com.example.bsagroupproject.data.Person
import kotlinx.coroutines.flow.MutableStateFlow

class ChatViewModel:ViewModel() {
    private val _personList = MutableStateFlow(listOf<Person>())
    val personList get() = _personList

    private val _selectedPersonProfile = MutableStateFlow(Person())
    val selectedPersonProfile get() = _selectedPersonProfile

    private val _messageList = MutableStateFlow(listOf<Message>())

    val messageList get() = _messageList


    fun getPersonList() {
        ChatOperations().getPersonList {
            _personList.value = it
            Log.d("person_list", personList.value.toString())
        }

    }

    fun updatePersonProfile(selectedProfile: Person) {
        _selectedPersonProfile.value = selectedProfile
        Log.d("selected_person", selectedPersonProfile.value.toString())
    }

    fun sendMessage(message: String, receiverUID: String) {
        ChatOperations().sendMessage(message, receiverUID)
    }

    fun receiveMessages(receiverUID: String) {
        ChatOperations().receiveMessages(receiverUID) {
            _messageList.value = it
            Log.d("receiveMessages", messageList.value.toString())
        }

    }
}
