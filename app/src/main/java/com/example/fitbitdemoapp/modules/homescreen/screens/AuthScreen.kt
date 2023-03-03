package com.example.fitbitdemoapp.modules.homescreen.screens

import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.fitbitdemoapp.BuildConfig
import com.example.fitbitdemoapp.utils.AuthWebViewClient
import com.example.fitbitdemoapp.utils.UrlChangeHandler

@Composable
fun AuthScreen(){
    val context= LocalContext.current

    val uri = "https://www.fitbit.com/oauth2/authorize" +
            "?response_type=code" +
            "&client_id=${BuildConfig.FITBIT_CLIENT_ID}" +
            "&scope=activity+cardio_fitness+electrocardiogram+heartrate+location+nutrition+oxygen_saturation+profile+respiratory_rate+settings+sleep+social+temperature+weight" +
            "&code_challenge=${BuildConfig.FITBIT_CODE_CHALLENGE}" +
            "&code_challenge_method=${BuildConfig.FITBIT_CODE_CHALLENGE_METHOD}" +
            "&state=${BuildConfig.FITBIT_STATE}"

    AndroidView(factory = {

        WebView(context).apply {

            webViewClient = AuthWebViewClient(object :UrlChangeHandler{
                override fun onUrlChanged(newUrl: String?) {
                    Log.d("NEW_URL", "onUrlChanged: $newUrl")
                }

                override fun onLoadError(errorCode: Int, description: CharSequence?) {
                }
            })

            settings.apply {
                domStorageEnabled = true
                javaScriptEnabled = true
            }

            loadUrl(uri)
        }
    })

}