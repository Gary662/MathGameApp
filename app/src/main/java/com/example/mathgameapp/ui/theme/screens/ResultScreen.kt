package com.example.mathgameapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment

@Composable
fun ResultScreen(
    correctAnswers: Int,
    wrongAnswers: Int,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally // Center button horizontally
    ) {
        Text(
            text = "Game Over!",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp) // Add bottom padding for spacing
        )
        Text("Correct Answers: $correctAnswers", style = MaterialTheme.typography.bodyLarge)
        Text("Wrong Answers: $wrongAnswers", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onRetry,
            modifier = Modifier.fillMaxWidth() // Make button fill the available width
        ) {
            Text("Try Again")
        }
    }
}
