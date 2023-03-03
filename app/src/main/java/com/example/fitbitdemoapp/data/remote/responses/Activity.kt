package com.example.fitbitdemoapp.data.remote.responses

data class Activity(
    val activityId: Int,
    val activityParentId: Int,
    val activityParentName: String,
    val calories: Int,
    val description: String,
    val distance: Double,
    val duration: Int,
    val hasActiveZoneMinutes: Boolean,
    val hasStartTime: Boolean,
    val isFavorite: Boolean,
    val lastModified: String,
    val logId: Long,
    val name: String,
    val startDate: String,
    val startTime: String,
    val steps: Int
)