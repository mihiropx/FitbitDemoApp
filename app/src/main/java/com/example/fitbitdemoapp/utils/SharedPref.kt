package com.example.fitbitdemoapp.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.dataStore
import com.example.fitbitdemoapp.data.remote.responses.AccessTokenResponse
import com.google.gson.Gson

object SharedPref {

    private const val FITBIT_ACCESS_DATA = "fitbitAccessData"
    private const val SHARED_PREF = "sharedPreferences"

    private fun getSharedPreference(context: Context): SharedPreferences? {
        var sp: SharedPreferences? = null
        try {
            sp = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        } catch (ignored: Exception) {

        }
        return sp
    }

    fun storeAccessData(context: Context, accessTokenResponse: AccessTokenResponse) {
        val prefsEditor = getSharedPreference(context)?.edit()
        val accessData = Gson().toJson(accessTokenResponse)
        prefsEditor?.putString(FITBIT_ACCESS_DATA, accessData)
        prefsEditor?.apply()
    }

    fun getAccessData(context: Context): AccessTokenResponse? {
        val accessData = getSharedPreference(context)?.getString(FITBIT_ACCESS_DATA, "")
        return if (accessData.isNullOrBlank())
            null
        else
            Gson().fromJson(
                accessData,
                AccessTokenResponse::class.java
            )
    }
}