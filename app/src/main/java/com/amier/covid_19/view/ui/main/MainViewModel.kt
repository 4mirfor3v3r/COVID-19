package com.amier.covid_19.view.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amier.covid_19.model.GlobalData
import com.amier.covid_19.model.Provinsi
import com.amier.covid_19.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private var globalPositive = MutableLiveData<GlobalData>()
    private var globalDeath = MutableLiveData<GlobalData>()
    private var globalRecovered = MutableLiveData<GlobalData>()
    private var idList = MutableLiveData<ArrayList<Provinsi>?>()
//    private var status = MutableLiveData(arrayListOf(false,false,false,false))
    private var status = MutableLiveData<ArrayList<Boolean>>()

    init {
        getGlobalPositive()
        getGlobalRecovered()
        getGlobalDeath()
        getIdList()
    }

    private fun getGlobalPositive(){
        status.value?.add(0,false)
        NetworkConfig.api().getPositive().enqueue(object :Callback<GlobalData>{
            override fun onFailure(call: Call<GlobalData>, t: Throwable) {
                status.value?.set(0,true)
                globalPositive.value = null
            }

            override fun onResponse(call: Call<GlobalData>, response: Response<GlobalData>) {
                if (response.isSuccessful){
                    status.value?.set(0,true)
                    globalPositive.value = response.body()
                }else{
                    status.value?.set(0,true)
                }
//                Log.e("eSTATUS","[${status.value!![0]}, ${status.value!![1]}, ${status.value!![2]} ${status.value!![3]}]")
            }
        })
    }
    private fun getGlobalRecovered(){
        status.value?.add(1,false)
        NetworkConfig.api().getRecovered().enqueue(object :Callback<GlobalData>{
            override fun onFailure(call: Call<GlobalData>, t: Throwable) {
                status.value?.set(1,true)
                globalRecovered.value = null
            }

            override fun onResponse(call: Call<GlobalData>, response: Response<GlobalData>) {
                if (response.isSuccessful){
                    status.value?.set(1,true)
                    globalRecovered.value = response.body()
                }else{
                    status.value?.set(1,true)
                }
//                Log.e("eSTATUS","[${status.value!![0]}, ${status.value!![1]}, ${status.value!![2]} ${status.value!![3]}]")
            }
        })
    }
    private fun getGlobalDeath(){
        status.value?.add(2,false)
        NetworkConfig.api().getDeath().enqueue(object :Callback<GlobalData>{
            override fun onFailure(call: Call<GlobalData>, t: Throwable) {
                status.value?.set(2,true)
                globalDeath.value = null
            }

            override fun onResponse(call: Call<GlobalData>, response: Response<GlobalData>) {
                if (response.isSuccessful){
                    status.value?.set(2,true)
                    globalDeath.value = response.body()
                }else{
                    status.value?.set(2,true)
                }
//                Log.e("eSTATUS","[${status.value!![0]}, ${status.value!![1]}, ${status.value!![2]} ${status.value!![3]}]")
            }
        })
    }
    private fun getIdList(){
        status.value?.add(3,false)
        NetworkConfig.api().getDataProvinsi().enqueue(object :Callback<ArrayList<Provinsi>>{
            override fun onFailure(call: Call<ArrayList<Provinsi>>, t: Throwable) {
                status.value?.set(3,true)
                idList.value = null
            }

            override fun onResponse(call: Call<ArrayList<Provinsi>>, response: Response<ArrayList<Provinsi>>) {
                if (response.isSuccessful){
                    status.value?.set(3,true)
                    idList.value = response.body()
                }else{
                    status.value?.set(3,true)
                }
//                Log.e("eSTATUS","[${status.value!![0]}, ${status.value!![1]}, ${status.value!![2]} ${status.value!![3]}]")
            }
        })
    }

    fun getProvinsi():MutableLiveData<ArrayList<Provinsi>?> = idList
    fun getDeath():MutableLiveData<GlobalData> = globalDeath
    fun getRecovered():MutableLiveData<GlobalData> = globalRecovered
    fun getPositive():MutableLiveData<GlobalData> = globalPositive
    fun getStatus():MutableLiveData<ArrayList<Boolean>> = status
}
