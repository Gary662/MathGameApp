// Question.kt
package com.example.mathgameapp

data class Question(val number1: Int, val number2: Int) {
    val answer: Int
        get() = number1 + number2
}
