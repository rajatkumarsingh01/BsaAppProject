package com.example.bsagroupproject.model


import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bsagroupproject.ChatOperations
import com.example.bsagroupproject.data.Message
import com.example.bsagroupproject.data.Person
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ChatViewModel : ViewModel() {
    private val _personList = MutableStateFlow(listOf<Person>())
    val personList get() = _personList

    private val _selectedPersonProfile = MutableStateFlow(Person())
    val selectedPersonProfile get() = _selectedPersonProfile

    private val _messageList = MutableStateFlow(listOf<Message>())
    val messageList get() = _messageList

    private val chatOperations = ChatOperations()

    private val _chatId = MutableStateFlow("")
    val chatId get() = _chatId


    fun getPersonList() {
        chatOperations.getPersonList {
            _personList.value = it
            Log.d("person_list", personList.value.toString())
        }
    }

    fun updatePersonProfile(selectedProfile: Person) {
        _selectedPersonProfile.value = selectedProfile
        Log.d("selected_person", selectedPersonProfile.value.toString())
    }

    // Add a function to fetch messages
    fun getMessages(messageId: String) {
        chatOperations.receiveMessages(messageId) { messages ->
            _messageList.value = messages.toMutableList()
            Log.d("message_list", messageList.value.toString())
        }
    }

    fun getChatNode(afterGettingNode: () -> Unit) {
        chatOperations.getMessageNode() {
            for (id in it) {
                if (id.contains(selectedPersonProfile.value.userID)) {
                    _chatId.value = id
                }
            }
            Log.d("chat_id", chatId.value)
            afterGettingNode()
        }
    }

    fun sendMessageCall(message: String) {
        getChatNode {
            var commonMessageId = ""
            if (chatId.value.isEmpty()) {
                commonMessageId =
                    FirebaseAuth.getInstance().currentUser?.uid + selectedPersonProfile.value.userID
            } else {
                commonMessageId = chatId.value
            }
            chatOperations.sendMessage(
                message,
                receiverUID = selectedPersonProfile.value.userID,
                commonMessageId
            )
        }
    }

    fun getMessagesUsingCor(messageId: String) {
//        chatOperations.receiveMessages(messageId) { messages ->
//            _messageList.value = messages.toMutableList()
//            Log.d("message_list", messageList.value.toString())
        try {
            viewModelScope.launch {
                val auth = FirebaseAuth.getInstance()
                val currentUser = auth.currentUser  // for current user
                val database = Firebase.database.reference
                val response = database.child("messages").child(messageId).get().await()
                val messages = mutableListOf<Message>()

                response.children.forEach {
                    val message = it.getValue(Message::class.java)
                    message?.let {
                        messages.add(it)
                    }
                }
                _messageList.value = messages
                Log.d("using_courutine", messageList.value.toString())
            }

        } catch (e: Exception) {
            Log.e("getMessagesUsingCor", "Error loading messages", e)
        }
    }
}


