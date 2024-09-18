package com.example.assignment2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignment2.ui.theme.Assignment2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment2Theme {
                val navController = rememberNavController()  // Initialize NavController
                NavHostScreen(navController)  // Call the NavHostScreen
            }
        }
    }
}

// NavHostScreen: Defines navigation routes
@Composable
fun NavHostScreen(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "main_screen") {
        composable("main_screen") { MainScreen(navController) }
        composable("second_screen") { SecondScreen(navController) }
    }
}

// MainScreen: Main activity with two buttons
@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Name: Elvis Acheampong",
            fontSize = 20.sp,
            lineHeight = 110.sp,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Student ID: 1321063",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Row to hold two buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Implicit Intent-like behavior: Navigate to second screen using navigation
            Button(
                onClick = {
                    navController.navigate("second_screen")  // Navigate to second screen
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Implicit")
            }

            // Explicit Intent: Navigate to second screen
            Button(
                onClick = {
                    navController.navigate("second_screen")  // Navigate explicitly to second screen
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Explicit")
            }
        }
    }
}

// SecondScreen: Shows challenges and a button to navigate back to the main screen
@Composable
fun SecondScreen(navController: NavController) {
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

        // List of challenges
        Text(text = "- Device fragmentation", fontSize = 18.sp)
        Text(text = "- Battery life optimization", fontSize = 18.sp)
        Text(text = "- Security and data privacy", fontSize = 18.sp)
        Text(text = "- Performance optimization", fontSize = 18.sp)
        Text(text = "- Cross-platform compatibility", fontSize = 18.sp, modifier = Modifier.padding(bottom = 24.dp))

        // Button to go back to the main screen
        Button(
            onClick = {
                navController.navigate("main_screen")  // Navigate back to main screen
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Main Activity")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainPreview() {
    val navController = rememberNavController()
    MainScreen(navController)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SecondScreenPreview() {
    val navController = rememberNavController()
    SecondScreen(navController)
}
