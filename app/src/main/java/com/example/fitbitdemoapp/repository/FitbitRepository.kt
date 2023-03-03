package com.example.fitbitdemoapp.repository

import com.example.fitbitdemoapp.data.remote.FitbitApi
import com.example.fitbitdemoapp.data.remote.requests.AccessTokenRequest
import com.example.fitbitdemoapp.data.remote.responses.AccessTokenResponse
import com.example.fitbitdemoapp.data.remote.responses.DailyActivityResponse
import com.example.fitbitdemoapp.utils.Resource
import javax.inject.Inject

class FitbitRepository @Inject constructor(
    private val api: FitbitApi
) {

    suspend fun getAccessToken(accessTokenRequest: AccessTokenRequest): Resource<AccessTokenResponse> {
        val response = try {
            api.getAccessToken(accessTokenRequest)
        } catch (e: Exception) {
            return Resource.Error("Something went wrong!")
        }
        return Resource.Success(response)
    }

    suspend fun getDailyActivities(date: String): Resource<DailyActivityResponse> {
        val response = try {
            api.getDailyActivities(date)
        } catch (e: Exception) {
            return Resource.Error("Something went wrong!")
        }
        return Resource.Success(response)
    }
}