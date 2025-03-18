package com.example.mathgameapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.mathgameapp.ui.screens.QuestionScreen
import com.example.mathgameapp.ui.screens.ResultScreen
import com.example.mathgameapp.ui.screens.StartScreen
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MathGameApp()
        }
    }
}

@Composable
fun MathGameApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "start") {
        composable("start") {
            StartScreen(onStart = { totalQuestions ->
                navController.navigate("game/$totalQuestions")
            })
        }
        composable("game/{totalQuestions}") { backStackEntry ->
            val totalQuestions = backStackEntry.arguments?.getString("totalQuestions")?.toInt() ?: 5
            var currentQuestionIndex by remember { mutableStateOf(0) }
            var correctAnswers by remember { mutableStateOf(0) }
            var wrongAnswers by remember { mutableStateOf(0) }

            // Generate random questions for the game
            val questions = List(totalQuestions) {
                val num1 = Random.nextInt(0, 100)  // Random number between 0 and 99
                val num2 = Random.nextInt(0, 100)
                Question(num1, num2)
            }

            if (currentQuestionIndex < totalQuestions) {
                QuestionScreen(
                    index = currentQuestionIndex,
                    totalQuestions = totalQuestions,
                    question = questions[currentQuestionIndex],  // Pass the current question
                    onNext = { isCorrect ->
                        if (isCorrect) correctAnswers++ else wrongAnswers++
                        currentQuestionIndex++
                    },
                    onCancel = {
                        // Navigate back to the start screen
                        navController.popBackStack("start", false)
                    },
                    correctAnswers = correctAnswers,
                    wrongAnswers = wrongAnswers
                )
            } else {
                navController.navigate("result/$correctAnswers/$wrongAnswers")
            }
        }
        composable("result/{correctAnswers}/{wrongAnswers}") { backStackEntry ->
            val correctAnswers = backStackEntry.arguments?.getString("correctAnswers")?.toInt() ?: 0
            val wrongAnswers = backStackEntry.arguments?.getString("wrongAnswers")?.toInt() ?: 0
            ResultScreen(correctAnswers, wrongAnswers) {
                navController.popBackStack("start", false)  // Navigate back to the start screen
            }
        }
    }
}
