package com.example.assignment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SecondScreen(onFinish = { finish() })
        }
    }
}

@Composable
fun SecondScreen(onFinish: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Mobile Software Engineering Challenges",
            fontSize = 22.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(text = "- Device fragmentation", fontSize = 18.sp)
        Text(text = "- Battery life optimization", fontSize = 18.sp)
        Text(text = "- Security and data privacy", fontSize = 18.sp)
        Text(text = "- Performance optimization", fontSize = 18.sp)
        Text(
            text = "- Cross-platform compatibility",
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Button(
            onClick = {
                // Navigate back to the main screen
                onFinish()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Main Activity")
        }
    }
}