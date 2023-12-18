package com.example.chat_app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MessageItem(msg: String, time: String, position: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        if (position) {
            Spacer(modifier = Modifier.weight(1f))
            Column(horizontalAlignment = Alignment.End) {
                Text(fontSize = 25.sp, text = msg)
                Text(fontSize = 17.sp, text = time)
            }
        } else {
            Column(horizontalAlignment = Alignment.Start) {
                Text(fontSize = 25.sp, text = msg)
                Text(fontSize = 17.sp, text = time)
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}