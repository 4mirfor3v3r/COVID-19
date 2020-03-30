package com.amier.covid_19.network

import com.amier.covid_19.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkConfig {
    companion object{
        private fun getInterceptor():OkHttpClient{
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

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
        fun api() = getNetwork().create(ApiService::class.java)
    }
}