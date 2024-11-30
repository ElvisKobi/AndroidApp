package com.example.assignment2

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignment2.ui.theme.Assignment2Theme

class MainActivity : ComponentActivity() {
    private val REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment2Theme {
                val navController = rememberNavController()
                NavHostScreen(navController, ::isPermissionGranted)
            }
        }

        // Request the custom permission if not already granted
        if (!isPermissionGranted("com.example.assignment2.permission.MSE412")) {
            Log.d("MainActivity", "Requesting permission: com.example.assignment2.permission.MSE412")
            ActivityCompat.requestPermissions(this,
                arrayOf("com.example.assignment2.permission.MSE412"),
                REQUEST_CODE)
        } else {
            Log.d("MainActivity", "Permission already granted: com.example.assignment2.permission.MSE412")
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with accessing the SecondActivity
                Log.d("MainActivity", "Permission granted")
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            } else {
                // Permission denied, handle accordingly
                Log.d("MainActivity", "Permission denied")
                showPermissionDeniedMessage()
            }
        }
    }

    private fun showPermissionDeniedMessage() {
        // Show a message to the user explaining why the permission is needed
        Toast.makeText(this, "Permission denied. Cannot access the SecondActivity.", Toast.LENGTH_LONG).show()
    }

    // Function to check if a specific permission is granted
    private fun isPermissionGranted(permission: String): Boolean {
        val granted = ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
        Log.d("MainActivity", "Permission $permission granted: $granted")
        return granted
    }
}

@Composable
fun NavHostScreen(navController: NavHostController, isPermissionGranted: (String) -> Boolean) {
    NavHost(navController = navController, startDestination = "main_screen") {
        composable("main_screen") { MainScreen(navController, isPermissionGranted) }
    }
}

@Composable
fun MainScreen(navController: NavController, isPermissionGranted: (String) -> Boolean) {
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = {
                    if (isPermissionGranted("com.example.assignment2.permission.MSE412")) {
                        val intent = Intent(navController.context, SecondActivity::class.java)
                        navController.context.startActivity(intent)
                    } else {
                        Log.d("MainScreen", "Requesting permission: com.example.assignment2.permission.MSE412")
                        ActivityCompat.requestPermissions(
                            navController.context as ComponentActivity,
                            arrayOf("com.example.assignment2.permission.MSE412"),
                            100
                        )
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Implicit")
            }

            Button(
                onClick = {
                    if (isPermissionGranted("com.example.assignment2.permission.MSE412")) {
                        val intent = Intent(navController.context, SecondActivity::class.java)
                        navController.context.startActivity(intent)
                    } else {
                        Log.d("MainScreen", "Requesting permission: com.example.assignment2.permission.MSE412")
                        ActivityCompat.requestPermissions(
                            navController.context as ComponentActivity,
                            arrayOf("com.example.assignment2.permission.MSE412"),
                            100
                        )
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Explicit")
            }
        }

        Button(
            onClick = {
                val intent = Intent(navController.context, ThirdActivity::class.java)
                navController.context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "View Image Activity")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainPreview() {
    val navController = rememberNavController()
    MainScreen(navController, { true }) // Dummy permission check for preview
}