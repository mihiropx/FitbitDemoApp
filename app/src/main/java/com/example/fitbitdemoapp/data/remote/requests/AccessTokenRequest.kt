package com.example.fitbitdemoapp.data.remote.requests

data class AccessTokenRequest(
    val client_id: String,
    val grant_type: String,
    val code: String,
    val code_verifier: String
)
