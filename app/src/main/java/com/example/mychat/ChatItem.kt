package com.example.chat_app

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.chat_app.Navigation.Screens

@Composable
fun ChatItem(name: String, navController: NavController) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(17.dp)
            .clickable {
                navController.navigate(route = Screens.Chat.getFullRoute(name = name))
            }) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Person Icon",
            Modifier.size(32.dp)
        )
        Text(fontSize = 24.sp, text = name)
    }
    Divider(modifier = Modifier.fillMaxWidth())
}