package com.amier.covid_19.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amier.covid_19.adapter.ListCountryRvAdapter
import com.amier.covid_19.adapter.MainRvAdapter
import com.amier.covid_19.model.CountryData
import com.amier.covid_19.model.Provinsi

object Extensions{
    @BindingAdapter("app:mainList")
    @JvmStatic
    fun setRepoList(recyclerView: RecyclerView, repoData: ArrayList<Provinsi>?){
        if (repoData!=null) {
            recyclerView.adapter = MainRvAdapter()
            recyclerView.isNestedScrollingEnabled = false
                with(recyclerView.adapter as MainRvAdapter) {
                    replaceData(repoData)
                }
        }
    }
    @BindingAdapter("app:countryList")
    @JvmStatic
    fun setCountryList(recyclerView: RecyclerView, repoData: ArrayList<CountryData>?){
        if (repoData!=null) {
            recyclerView.adapter = ListCountryRvAdapter()
            with(recyclerView.adapter as ListCountryRvAdapter) {
                replaceData(repoData)
            }
        }
    }
}