package com.amier.covid_19.network

import android.app.Application
import android.content.pm.ApplicationInfo
import android.os.Build
import com.amier.covid_19.BuildConfig
import com.amier.covid_19.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import java.util.jar.Manifest

class NetworkConfig {
    companion object{
        private fun getInterceptor():OkHttpClient{
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG)
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            else
                loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE

            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(30,TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build()
        }
        private fun getNetwork():Retrofit{
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(getInterceptor())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        fun api(): ApiService = getNetwork().create(ApiService::class.java)
    }
}