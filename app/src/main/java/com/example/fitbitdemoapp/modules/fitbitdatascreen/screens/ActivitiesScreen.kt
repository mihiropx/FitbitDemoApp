package com.example.fitbitdemoapp.modules.fitbitdatascreen.screens

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitbitdemoapp.BuildConfig
import com.example.fitbitdemoapp.data.remote.requests.AccessTokenRequest
import com.example.fitbitdemoapp.modules.fitbitdatascreen.viewmodel.FitbitViewModel
import com.example.fitbitdemoapp.ui.theme.FitbitDemoAppTheme
import com.google.firebase.dynamiclinks.PendingDynamicLinkData
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase

lateinit var viewModel: FitbitViewModel

@Preview(showBackground = true)
@Composable
fun ActivitiesScreenPreview() {
    FitbitDemoAppTheme {
        ActivitiesScreen()
    }
}

@Composable
fun ActivitiesScreen() {
     viewModel = hiltViewModel()
    val context = LocalContext.current

    val summary = viewModel.summary.observeAsState()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "${summary.value?.steps}", fontSize = 25.sp)
        Text(text = "Steps")
    }
}