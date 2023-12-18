package com.example.chat_app.Database

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.input.TextFieldValue
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Message(
    val from: String?,
    val to: String?,
    val msg: String?,
    val date: String?,
) {
    constructor() : this(null, null, null, null)
}

class Main {
    companion object {
        private val users = FirebaseDatabase.getInstance().reference.child("users")
        private val messages = FirebaseDatabase.getInstance().reference.child("messages")

        fun saveUser(context: Context, user: String) {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("db", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("user", user)
            editor.apply()
        }

        fun getSavedUser(context: Context): String {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("db", Context.MODE_PRIVATE)
            return sharedPreferences.getString("user", "") ?: ""
        }

        fun setPassword(user: String, password: String) {
            users.child(user).setValue(password)
        }

        fun createUser(user: TextFieldValue, password: TextFieldValue) {
            users.child(user.text).setValue(password.text)
        }

        fun getUsers(callback: (List<String>) -> Unit) {
            users.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val users = mutableListOf<String>()
                    for (snapshot in dataSnapshot.children) {
                        users.add(snapshot.key.toString())
                    }
                    callback(users)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    callback(emptyList())
                }
            })
        }

        fun getUser(user: TextFieldValue, password: TextFieldValue, callback: (String) -> Unit) {
            users.child(user.text).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists() && dataSnapshot.getValue(String::class.java) == password.text) {

                        callback("Successful Login")
                    } else {
                        callback("Incorrect Username or Password")
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    callback("Database Error")
                }
            })
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun sendMessage(from: String, to: String, msg: String) {
            val currentDateTime = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val formattedDateTime = currentDateTime.format(formatter)
            val data = Message(from, to, msg, formattedDateTime)
            messages.push().setValue(data)
        }

        fun getMessages(chat: String, user: String, callback: (List<Message>) -> Unit) {
            messages.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val messages = mutableListOf<Message>()
                    val children = dataSnapshot.children
                    children.forEach {
                        val message = it.getValue(Message::class.java)
                        if (message != null) {
                            if ((message.from == chat && message.to == user) || (message.from == user && message.to == chat)) {
                                messages.add(message)
                            }
                        }
                    }
                    callback(messages)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    callback(emptyList())
                }
            })
        }
    }
}