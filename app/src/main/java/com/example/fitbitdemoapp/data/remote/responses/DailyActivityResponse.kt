package com.example.fitbitdemoapp.data.remote.responses

data class DailyActivityResponse(
    val activities: List<Activity>,
    val goals: Goals,
    val summary: Summary
)