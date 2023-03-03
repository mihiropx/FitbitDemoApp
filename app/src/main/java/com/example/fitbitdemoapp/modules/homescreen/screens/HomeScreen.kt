package com.example.fitbitdemoapp.ui.screens

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitbitdemoapp.BuildConfig
import com.example.fitbitdemoapp.R
import com.google.firebase.dynamiclinks.ktx.androidParameters
import com.google.firebase.dynamiclinks.ktx.dynamicLink
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.dynamiclinks.ktx.iosParameters
import com.google.firebase.ktx.Firebase

@Preview(showBackground = true)
@Composable
fun HomeScreen() {

    val uriHandler = LocalUriHandler.current
    val uri = "https://www.fitbit.com/oauth2/authorize" +
            "?response_type=code" +
            "&client_id=${BuildConfig.FITBIT_CLIENT_ID}" +
            "&scope=activity+cardio_fitness+electrocardiogram+heartrate+location+nutrition+oxygen_saturation+profile+respiratory_rate+settings+sleep+social+temperature+weight" +
            "&code_challenge=${BuildConfig.FITBIT_CODE_CHALLENGE}" +
            "&code_challenge_method=${BuildConfig.FITBIT_CODE_CHALLENGE_METHOD}" +
            "&state=${BuildConfig.FITBIT_STATE}"

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Connect with", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Spacer(modifier = Modifier.weight(2f))
            Button(modifier = Modifier.weight(3f), onClick = {

                uriHandler.openUri(uri)
                Log.d("TAG", "HomeScreen: $uri")

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

