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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ChatViewModel : ViewModel() {
    //firebase
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser  // for current user
    val database = Firebase.database.reference

    private val _personList = MutableStateFlow(listOf<Person>())
    val personList get() = _personList

    private val _selectedPersonProfile = MutableStateFlow(Person())
    val selectedPersonProfile get() = _selectedPersonProfile

    private val _messageList = MutableStateFlow(listOf<Message>())
    val messageList get() = _messageList

    private val chatOperations = ChatOperations()

    private val _chatId = MutableStateFlow("")
    val chatId get() = _chatId

    private val _userName = MutableStateFlow("")
    val userName get() = _userName

    private val _chatNodeList=MutableStateFlow(mutableListOf<String>())
    val chatNodeList get() =_chatNodeList




    fun getUserName() {
        viewModelScope.launch {
            currentUser?.let {
                val response = database.child("user").child(it.uid).child("userName").get()
                val finalResponse = response.await()
                if (finalResponse.exists()) {
                    _userName.value = finalResponse.value.toString()
                } else {
                    Log.d("user_name_failure", "unable to get username")
                }

            }
        }

    }


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
//    fun getMessages(messageId: String) {
//        database.child("messages").child(messageId).addValueEventListener(object :
//            ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val messages = mutableListOf<Message>()
//                if (snapshot.exists()) {
//                    for (data in snapshot.children) {
//                        val message = data.getValue(Message::class.java)
//                        if (message != null) {
//                            messages.add(message)
//                        }
//                    }
//                    Log.d("updated_message", messages.toString())
//                    _messageList.value = messages.toMutableList()
//                }else{
//                    _messageList.value=messages
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.d("receiveMessages", error.message)
//            }
//        })
//    }

    fun getMessages(messageId: String) {
        database.child("messages").child(messageId).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val messages = mutableListOf<Message>()
                if (snapshot.exists()) {
                    for (data in snapshot.children) {
                        val message = data.getValue(Message::class.java)
                        if (message != null) {
                            messages.add(message)
                        }
                    }
                    Log.d("updated_message", messages.toString())
                    _messageList.value = messages.toMutableList()
                } else {
                    _messageList.value = emptyList()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("receiveMessages", error.message)
            }
        })
    }


    fun getChatNode(afterGettingNode: () -> Unit) {
        chatOperations.getMessageNode() {
            _chatNodeList.value = it.map { it.toString() }.toMutableList()
          setChatId(selectedPersonProfile.value.userID)
            afterGettingNode()
        }
    }
//
//    fun setChatId(userId:String){
//        for (id in chatNodeList.value) {
//                if (id.contains(userId)) {
//                    _chatId.value = id
//                }else{
//                    _chatId.value=""
//                }
//            }
//            Log.d("set_chat_id", chatId.value)
//    }


    fun setChatId(userId: String) {
        for (id in chatNodeList.value) {
            if (id.contains(userId) && id.contains(selectedPersonProfile.value.userID)) {
                _chatId.value = id
                return
            }
        }
        _chatId.value = ""
        Log.d("set_chat_id", chatId.value)
    }


//    fun sendMessageCall(message: String) {
//        getChatNode {
//            var commonMessageId = ""
//            if (chatId.value.isEmpty()) {
//                commonMessageId =
//                    FirebaseAuth.getInstance().currentUser?.uid + selectedPersonProfile.value.userID
//            } else {
//                commonMessageId = chatId.value
//            }
//            chatOperations.sendMessage(
//                message,
//                receiverUID = selectedPersonProfile.value.userID,
//                commonMessageId
//            )
//        }
//    }

    fun sendMessageCall(messageContent: String) {
        getChatNode {
            val commonMessageId = if (chatId.value.isEmpty()) {
                FirebaseAuth.getInstance().currentUser?.uid + selectedPersonProfile.value.userID
            } else {
                chatId.value
            }
            chatOperations.sendMessage(
                messageContent,
                receiverUID = selectedPersonProfile.value.userID,
                commonMessageId
            )
        }
    }

}


