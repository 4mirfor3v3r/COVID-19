package com.amier.covid_19.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.amier.covid_19.R
import com.amier.covid_19.databinding.ItemListCountryBinding
import com.amier.covid_19.model.CountryData

class ListCountryRvAdapter:RecyclerView.Adapter<ListCountryRvAdapter.Holder>() {
    private var listC:ArrayList<CountryData>? = arrayListOf()

    class Holder(itemListCountryBinding: ItemListCountryBinding):RecyclerView.ViewHolder(itemListCountryBinding.root) {
        private val binding = itemListCountryBinding
        fun bindItem(data: CountryData) {
            binding.data = data.attributes
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding:ItemListCountryBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_list_country,parent,false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return listC?.size!!
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = listC?.get(position)
        if (data != null) {
            holder.bindItem(data)
        }
    }

    fun replaceData(repoD:ArrayList<CountryData>?){
        setList(repoD)
    }

    private fun setList(repoD: java.util.ArrayList<CountryData>?) {
        this.listC = repoD
        notifyDataSetChanged()
    }
}