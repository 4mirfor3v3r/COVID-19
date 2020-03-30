package com.amier.covid_19.view.ui.listcountry

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amier.covid_19.model.CountryData
import com.amier.covid_19.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.KClass

class ListCountryViewModel : ViewModel() {
//    val activityToStart = MutableLiveData<Pair<KClass<*>, Bundle?>>()

    private var listC = MutableLiveData<ArrayList<CountryData>>()
    init {
        getCountryDataApi()
    }
    private fun getCountryDataApi(){
        NetworkConfig.api().getCountryData().enqueue(object :Callback<ArrayList<CountryData>>{
            override fun onFailure(call: Call<ArrayList<CountryData>>, t: Throwable) {
                listC.value = null
            }

            override fun onResponse(call: Call<ArrayList<CountryData>>, response: Response<ArrayList<CountryData>>) {
                if (response.isSuccessful){
                    listC.value = response.body()
                }
            }
        })
    }

    fun getCountryData():MutableLiveData<ArrayList<CountryData>> = listC
}
