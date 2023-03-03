package com.example.fitbitdemoapp.data.remote.responses

data class Summary(
    val activeScore: Int,
    val activityCalories: Int,
    val caloriesBMR: Int,
    val caloriesOut: Int,
    val distances: List<Distance>,
    val fairlyActiveMinutes: Int,
    val lightlyActiveMinutes: Int,
    val marginalCalories: Int,
    val sedentaryMinutes: Int,
    val steps: Int,
    val veryActiveMinutes: Int
)