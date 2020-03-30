package com.amier.covid_19.network

import com.amier.covid_19.model.CountryData
import com.amier.covid_19.model.GlobalData
import com.amier.covid_19.model.Provinsi
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("positif")
    fun getPositive():Call<GlobalData>
    @GET("sembuh")
    fun getRecovered():Call<GlobalData>
    @GET("meninggal")
    fun getDeath():Call<GlobalData>
    @GET("indonesia/provinsi")
    fun getDataProvinsi(): Call<ArrayList<Provinsi>>
    @GET("/")
    fun getCountryData():Call<ArrayList<CountryData>>
}