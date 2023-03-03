package com.example.fitbitdemoapp.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object AuthScreen : Screen("auth_screen")
}
