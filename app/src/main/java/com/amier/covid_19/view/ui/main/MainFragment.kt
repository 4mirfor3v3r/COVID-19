package com.amier.covid_19.view.ui.main

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.amier.covid_19.R
import com.amier.covid_19.databinding.MainFragmentBinding
import com.amier.covid_19.model.Attributes
import com.amier.covid_19.model.Provinsi
import com.amier.covid_19.view.ListCountry
import kotlinx.android.synthetic.main.main_fragment.view.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    var positive = MutableLiveData<String>()
    var recovered = MutableLiveData<String>()
    var death = MutableLiveData<String>()
    var idList = MutableLiveData<ArrayList<Provinsi>>()

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.lifecycleOwner = this
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.fm = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        startPB()
//        viewModel.getStatus().observe(viewLifecycleOwner, Observer {
//            Log.e("STATUS","[${it[0]}, ${it[1]}, ${it[2]} ${it[3]}]")
//            if (it[0] && it[1] &&it[2]){
//                animate()
//                Handler().postDelayed({
//                    setGlobalData()
//                    stopPB()
//                },400)
//            }
//            if (it[3]){
//                viewModel.getProvinsi().observe(viewLifecycleOwner, Observer {repoData ->
//                    if (repoData!=null) {
//                        idList.value = repoData
//                    }
//                })
//            }
//        })
        animate()
        Handler().postDelayed({
            setGlobalData()
        }, 1600)

        viewModel.getProvinsi().observe(viewLifecycleOwner, Observer { repoData ->
            if (repoData != null) {
                idList.value = repoData
                binding.mainRv.startLayoutAnimation()
            }
        })

        binding.tvGlobalDetail.setOnClickListener {
            val i = Intent(this.context, ListCountry::class.java)
            startActivity(i)
            activity?.overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top)
        }
    }

    private fun animate() {
        Handler().postDelayed({
            stopPB()
            binding.cardView.startAnimation(AnimationUtils.loadAnimation(this.context, R.anim.scale_up))
            binding.textView7.visibility = View.VISIBLE
            binding.textView7.startAnimation(AnimationUtils.loadAnimation(this.context, R.anim.translate_up))
            Handler().postDelayed({
                Handler().postDelayed({
                    binding.textView2.visibility = View.VISIBLE
                    binding.textView2.startAnimation(AnimationUtils.loadAnimation(this.context, R.anim.translate_down))
                    Handler().postDelayed({
                        binding.textView.visibility = View.VISIBLE
                        binding.textView.startAnimation(AnimationUtils.loadAnimation(this.context, R.anim.translate_down))
                    }, 400)
                }, 400)
            }, 800)
        }, 200)
    }

    private fun stopPB() {
        binding.pb.visibility = View.GONE
        binding.bgPB.visibility = View.GONE
        binding.tvGlobalDetail.isEnabled = true
        binding.pb.isIndeterminate = false
    }

    private fun startPB() {
        binding.pb.visibility = View.VISIBLE
        binding.bgPB.visibility = View.VISIBLE
        binding.tvGlobalDetail.isEnabled = false
        binding.pb.isIndeterminate = true
    }

    private fun setGlobalData() {
        viewModel.getPositive().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                positive.value = "${it.value}\nConfirmed"
            }
        })
        viewModel.getRecovered().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                recovered.value = "${it.value}\nRecovered"
            }
        })
        viewModel.getDeath().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                death.value = "${it.value}\nDeath"
            }
        })
    }

}
