package com.example.fitbitdemoapp.data.remote

import com.example.fitbitdemoapp.data.remote.requests.AccessTokenRequest
import com.example.fitbitdemoapp.data.remote.responses.AccessTokenResponse
import com.example.fitbitdemoapp.data.remote.responses.DailyActivityResponse
import retrofit2.http.*

interface FitbitApi {

    @Headers("Authorization: Basic MjNRVEg0OjQyZDJlZmVkNGQ2MzJlZTcwYTdmMmU5NWVlMzhjZTY0")
    @POST("oauth2/token")
    suspend fun getAccessToken(
        @Body accessTokenRequest: AccessTokenRequest
    ): AccessTokenResponse

    @GET("1/user/-/activities/date/{date}.json")
    suspend fun getDailyActivities(
        @Path("date") date: String
    ): DailyActivityResponse

}