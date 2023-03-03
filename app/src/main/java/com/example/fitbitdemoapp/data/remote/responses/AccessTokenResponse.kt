package com.example.fitbitdemoapp.data.remote.responses

data class AccessTokenResponse(
    val access_token: String,
    val expires_in: Int,
    val refresh_token: String,
    val scope: String,
    val token_type: String,
    val user_id: String
)