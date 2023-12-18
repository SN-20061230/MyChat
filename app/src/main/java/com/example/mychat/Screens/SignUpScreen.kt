package com.example.chat_app.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.example.chat_app.Database.Main

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController) {
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Sign Up")
        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
            },
            label = { Text(text = "Your Username") },
            placeholder = { Text(text = "Username") },
        )
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = { Text(text = "Your Password") },
            placeholder = { Text(text = "Password") },
        )
        Button(onClick = {
            Main.createUser(username, password)
            Main.saveUser(context, username.text)
            navController.navigate("Home")
        }) {
            Text(text = "Submit")
        }
        Button(onClick = {
            navController.navigate("SignIn")
        }) {
            Text(text = "Sign In")
        }
    }
}