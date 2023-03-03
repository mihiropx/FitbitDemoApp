package com.example.fitbitdemoapp.modules.fitbitdatascreen.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitbitdemoapp.data.remote.requests.AccessTokenRequest
import com.example.fitbitdemoapp.data.remote.responses.Summary
import com.example.fitbitdemoapp.repository.FitbitRepository
import com.example.fitbitdemoapp.utils.Resource
import com.example.fitbitdemoapp.utils.SharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class FitbitViewModel @Inject public constructor(private val repository: FitbitRepository) : ViewModel() {

    var isLoading by mutableStateOf(false)
    var summary = MutableLiveData<Summary>()


    fun getFitbitAccessToken(context: Context, accessTokenRequest: AccessTokenRequest) {
        viewModelScope.launch {

            repository.getAccessToken(accessTokenRequest)
                .also { result ->

                    isLoading = when (result) {
                        is Resource.Success -> {
                            SharedPref.storeAccessData(context, result.data!!)
                            getDailyActivities()
                            false
                        }
                        is Resource.Error -> {
                            Log.e("33", "getFitbitAccessToken: ${result.message}")
                            false
                        }
                        is Resource.Loading -> {
                            true
                        }
                    }

                }

        }
    }

    private fun getDailyActivities() {
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            .format(System.currentTimeMillis())

        viewModelScope.launch {
            repository.getDailyActivities(date)
                .also { result ->

                    isLoading = when (result) {
                        is Resource.Success -> {
                            summary.postValue(result.data?.summary)
                            false
                        }
                        is Resource.Error -> {
                            Log.e("33", "getFitbitAccessToken: ${result.message}")
                            false
                        }
                        is Resource.Loading -> {
                            true
                        }
                    }
                }
        }
    }
}