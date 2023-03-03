package com.example.fitbitdemoapp.utils

interface UrlChangeHandler {
    fun onUrlChanged(newUrl: String?)
    fun onLoadError(errorCode: Int, description: CharSequence?)
}
