package com.example.fitbitdemoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fitbitdemoapp.modules.homescreen.screens.AuthScreen
import com.example.fitbitdemoapp.modules.homescreen.screens.HomeScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController)
        }

        composable(Screen.AuthScreen.route) {
            AuthScreen()
        }
    }

}