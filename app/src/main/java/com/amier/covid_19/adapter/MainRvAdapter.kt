package com.amier.covid_19.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.amier.covid_19.R
import com.amier.covid_19.databinding.ItemMainBinding
import com.amier.covid_19.model.Provinsi

class MainRvAdapter: RecyclerView.Adapter<MainRvAdapter.Holder>(){
    private var list: ArrayList<Provinsi>? = arrayListOf()
    class Holder(itemMainBinding: ItemMainBinding):RecyclerView.ViewHolder(itemMainBinding.root) {
        private val binding = itemMainBinding
        fun bindRows(data: Provinsi) {
            binding.data = data.attributes
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemMainBinding:ItemMainBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_main, parent,false)
        return Holder(itemMainBinding)
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list?.get(position)
        list?.forEach {
            Log.e("LIST", "+ $it")
        }
        if (data != null) {
            holder.bindRows(data)
        }
    }


    fun replaceData(repoD: ArrayList<Provinsi>?) {
        setList(repoD)
    }

    private fun setList(repoD: ArrayList<Provinsi>?) {
        this.list = repoD
        notifyDataSetChanged()
    }
}