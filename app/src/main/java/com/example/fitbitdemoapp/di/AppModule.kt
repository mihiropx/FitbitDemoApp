package com.example.fitbitdemoapp.di

import android.content.Context
import com.example.fitbitdemoapp.BuildConfig
import com.example.fitbitdemoapp.FitbitDemoApp
import com.example.fitbitdemoapp.data.remote.FitbitApi
import com.example.fitbitdemoapp.repository.FitbitRepository
import com.example.fitbitdemoapp.utils.Constants
import com.example.fitbitdemoapp.utils.SharedPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFitbitRepository(api: FitbitApi) = FitbitRepository(api)

    @Provides
    @Singleton
    fun provideFitbitApi(@ApplicationContext context: Context): FitbitApi {
        val httpClient = OkHttpClient.Builder()

        httpClient.readTimeout(10, TimeUnit.MINUTES)
        httpClient.connectTimeout(1, TimeUnit.MINUTES)
        httpClient.writeTimeout(10, TimeUnit.MINUTES)

        httpClient.addInterceptor { chain ->

            val original = chain.request()
            val builder = original.newBuilder()

            builder.header("Accept", "application/json")

            SharedPref.getAccessData(context)?.access_token?.let {token->
                builder.header("Authentication", token)
            }

            builder.method(original.method, original.body)
            chain.proceed(builder.build())
        }

        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG)
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        else
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        httpClient.addInterceptor(interceptor)

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .client(httpClient.build())
            .build()
            .create(FitbitApi::class.java)
    }
}