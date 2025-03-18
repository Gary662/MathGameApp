package com.example.mathgameapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TextField
import androidx.compose.ui.text.input.KeyboardType
import com.example.mathgameapp.Question


@Composable
fun QuestionScreen(
    index: Int,
    totalQuestions: Int,
    question: Question,  // Pass in the random question
    onNext: (Boolean) -> Unit,
    onCancel: () -> Unit,
    correctAnswers: Int,
    wrongAnswers: Int
) {
    var answer by remember { mutableStateOf("") }
    val correctAnswer = question.answer

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Question ${index + 1} of $totalQuestions")
        Text("What is ${question.number1} + ${question.number2}?")

        TextField(
            value = answer,
            onValueChange = { answer = it },
            label = { Text("Your answer") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onCancel) {
                Text("Cancel")
            }
            Button(onClick = {
                val isCorrect = answer.toIntOrNull() == correctAnswer  // Check if the answer is correct
                onNext(isCorrect)
            }) {
                Text("Next")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Correct Answers: $correctAnswers")
        Text("Wrong Answers: $wrongAnswers")
    }
}
