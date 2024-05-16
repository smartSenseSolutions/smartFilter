package com.ss.smartfilter.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * created by Mala Ruparel ON 16/05/24
 */
@Composable
fun TipBox() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = Color(0xFFEEEEEE), shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Checkbox
            val checkedState = remember { mutableStateOf(false) }
            Checkbox(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it },
                modifier = Modifier.padding(end = 18.dp)
            )

            Column {
                Text(
                    text = "Top Pick",
                    modifier = Modifier.padding(bottom = 4.dp),
                    fontSize = 16.sp
                )
                Text(
                    text = "These are buses with the highest ratings, best services and are most popular among travellers",
                    modifier = Modifier.padding(bottom = 4.dp),
                    fontSize = 14.sp
                )
            }

            // Small star image

        }
    }
}


