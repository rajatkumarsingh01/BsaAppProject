package com.example.bsagroupproject

import android.util.Log
import com.example.bsagroupproject.data.Message
import com.example.bsagroupproject.data.Person
import com.example.bsagroupproject.data.UserData
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class ChatOperations {
    // Initialize Firebase Auth
    private val auth = FirebaseAuth.getInstance()
    private val currentUser = auth.currentUser  // for current user
    private val database = Firebase.database.reference

    fun writeUserData(userData: UserData) {
        currentUser?.let { user ->
            database.child("user").child(user.uid).setValue(userData).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("userData", "userData return successfully")
                } else {
                    Log.d("userData", "problem writing data")
                }
            }
        }
    }

    fun getPersonList(person: (List<Person>) -> Unit) {
        database.child("user").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listOfPerson = mutableListOf<Person>()
                if (snapshot.exists()) {
                    for (data in snapshot.children) {
                        val personList = data.getValue(Person::class.java)
                        if (personList != null && personList.userID != currentUser?.uid) {
                            listOfPerson.add(personList)
                        }
                    }
                    Log.d("inside_person", listOfPerson.toString())
                    person(listOfPerson)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("error_job_post", error.message)
            }
        })
    }

    fun sendMessage(message: String, receiverUID: String, commonMessageId: String) {
        currentUser?.let { user ->
            val messageData = Message(message, user.uid, receiverUID, false)

            // Saving message key to sender node
            database.child("user").child(user.uid).child("chat").child(commonMessageId)
                .setValue(commonMessageId).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("sendMessage", "Message sent successfully to sender node")
                    } else {
                        Log.d("sendMessage", "Problem sending message to sender node")
                    }
                }

            // Saving messages to receiver node
            database.child("user").child(receiverUID).child("chat").child(commonMessageId)
                .setValue(commonMessageId).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("sendMessage", "Message received successfully to receiver node")
                    } else {
                        Log.d("sendMessage", "Problem receiving message to receiver node")
                    }
                }

            val messageKey = database.child("messages").push().key  // generate unique key for message
            if (messageKey != null) {
                database.child("messages").child(commonMessageId).child(messageKey)
                    .setValue(messageData)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("sendMessage", "Message sent successfully to messages node")
                        } else {
                            Log.d("sendMessage", "Problem sending message to messages node")
                        }
                    }
            }
        }
    }

    fun getMessageNode(chatId: (List<String>) -> Unit) {
        currentUser?.let { user ->
            database.child("user").child(user.uid).child("chat").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val listOfChat = mutableListOf<String>()
                    if (snapshot.exists()) {
                        for (data in snapshot.children) {
                            val chatList = data.getValue(String::class.java)
                            if (chatList != null) {
                                listOfChat.add(chatList)
                            }
                        }
                        Log.d("inside_get_message_node", listOfChat.toString())
                        chatId(listOfChat)
                    } else {
                        chatId(listOfChat)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("under_get_message_node", error.message)
                }
            })
        }
    }
}
