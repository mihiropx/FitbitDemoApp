package com.example.fitbitdemoapp.modules.fitbitdatascreen

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fitbitdemoapp.BuildConfig
import com.example.fitbitdemoapp.data.remote.requests.AccessTokenRequest
import com.example.fitbitdemoapp.modules.fitbitdatascreen.screens.ActivitiesScreen
import com.example.fitbitdemoapp.modules.fitbitdatascreen.viewmodel.FitbitViewModel
import com.example.fitbitdemoapp.ui.theme.FitbitDemoAppTheme
import com.google.firebase.dynamiclinks.PendingDynamicLinkData
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FitbitDataActivity : ComponentActivity() {

    private lateinit var viewModel: FitbitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FitbitDemoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    viewModel = hiltViewModel()

                    Firebase.dynamicLinks
                        .getDynamicLink(intent)
                        .addOnSuccessListener(this) { pendingDynamicLinkData: PendingDynamicLinkData? ->
                            // Get deep link from result (may be null if no link is found)
                            val deepLink: Uri?

                            if (pendingDynamicLinkData != null) {
                                deepLink = pendingDynamicLinkData.link

                                val code = deepLink?.getQueryParameter("code")
                                val state = deepLink?.getQueryParameter("state")

                                val accessTokenRequest = AccessTokenRequest(
                                    client_id = BuildConfig.FITBIT_CLIENT_ID,
                                    code = code ?: "",
                                    grant_type = "authorization_code",
                                    code_verifier = BuildConfig.FITBIT_CODE_VERIFIER
                                )

                             //   viewModel.getFitbitAccessToken(this, accessTokenRequest)

                            }

                        }
                        .addOnFailureListener(this) { e ->
                            Log.w(
                                "TAG",
                                "getDynamicLink:onFailure",
                                e
                            )
                        }

                    ActivitiesScreen()
                }
            }
        }
    }
}
