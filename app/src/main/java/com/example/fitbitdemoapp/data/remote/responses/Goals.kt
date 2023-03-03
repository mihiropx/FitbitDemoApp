package com.example.fitbitdemoapp.data.remote.responses

data class Goals(
    val activeMinutes: Int,
    val caloriesOut: Int,
    val distance: Double,
    val steps: Int
)