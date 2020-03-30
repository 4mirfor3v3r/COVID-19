package com.amier.covid_19.view.ui.listcountry

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.amier.covid_19.R
import com.amier.covid_19.databinding.ListCountryFragmentBinding
import com.amier.covid_19.model.CountryData
import com.amier.covid_19.view.MapsActivity
import kotlinx.android.synthetic.main.list_country_fragment.*

class ListCountryFragment : Fragment() {

    companion object {
        fun newInstance() = ListCountryFragment()
    }

    private lateinit var viewModel: ListCountryViewModel
    private lateinit var binding :ListCountryFragmentBinding
    var countryList = MutableLiveData<ArrayList<CountryData>>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.list_country_fragment,container,false)
        binding.lifecycleOwner = this
        viewModel = ViewModelProviders.of(this).get(ListCountryViewModel::class.java)
        binding.fm = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getCountryData().observe(viewLifecycleOwner, Observer {
            countryList.value = it
            binding.listCountryRv.startLayoutAnimation()
        })
        btnListCountry.setOnClickListener {
            startActivity(Intent(this.context,MapsActivity::class.java))
            activity?.overridePendingTransition(R.anim.slide_from_bottom,R.anim.slide_to_top)
        }

//        viewModel.activityToStart.observe(viewLifecycleOwner, Observer {
//            val intent = Intent(this.context,it.first::class.java)
//        })
    }

}
