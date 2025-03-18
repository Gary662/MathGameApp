package com.example.mathgameapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

@Composable
fun StartScreen(onStart: (Int) -> Unit) {
    var questionCount by remember { mutableStateOf("5") }
    var showError by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Enter number of questions:",
            style = TextStyle(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = questionCount,
            onValueChange = { questionCount = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        // Display error message if invalid input
        if (showError) {
            Text(text = "Please enter a valid number", color = Color.Red)
        }

        Button(
            onClick = {
                questionCount.toIntOrNull()?.let {
                    onStart(it)
                    keyboardController?.hide() // Dismiss the keyboard
                } ?: run {
                    showError = true
                }
            },
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        ) {
            Text("Start")
        }
    }
}

@Preview
@Composable
fun StartScreenPreview() {
    StartScreen(onStart = {})
}
