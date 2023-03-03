package com.example.fitbitdemoapp.modules.homescreen.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fitbitdemoapp.R
import com.example.fitbitdemoapp.navigation.Screen

@Composable
fun HomeScreen(navController: NavHostController) {

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Connect with", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Spacer(modifier = Modifier.weight(2f))
            Button(modifier = Modifier.weight(3f), onClick = {

                navController.navigate(Screen.AuthScreen.route)

            }) {
                Image(
                    painter = painterResource(id = R.drawable.fitbit_logo_white),
                    contentDescription = "Fitbit logo"
                )
            }
            Spacer(modifier = Modifier.weight(2f))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}