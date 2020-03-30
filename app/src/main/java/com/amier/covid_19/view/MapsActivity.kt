package com.amier.covid_19.view

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.amier.covid_19.R
import com.amier.covid_19.model.CountryData
import com.amier.covid_19.network.NetworkConfig

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var listC = MutableLiveData<ArrayList<CountryData>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fetchData()
    }

//    val task = @SuppressLint("StaticFieldLeak")
//    object :AsyncTask<Void,Void,MutableLiveData<ArrayList<CountryData>>>() {
//        override fun doInBackground(vararg params: Void?): MutableLiveData<ArrayList<CountryData>> {
//
//        }
//    }
    private fun fetchData() {
        NetworkConfig.api().getCountryData().enqueue(object :Callback<ArrayList<CountryData>>{
            override fun onFailure(call: Call<ArrayList<CountryData>>, t: Throwable) {
                Toast.makeText(this@MapsActivity,"Connection Failure",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ArrayList<CountryData>>, response: Response<ArrayList<CountryData>>) {
                listC.value = response.body()
            }
        })
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        listC.observe(this, Observer {list ->
            list.forEach {
                if (it.attributes.OBJECTID == 87) {
                    val lCam = LatLng(it.attributes.Lat,it.attributes.Long_)
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(lCam))
                }
                val latLng = LatLng(it.attributes.Lat,it.attributes.Long_)
                mMap.addMarker(MarkerOptions().position(latLng).title("${it.attributes.Country_Region} ${it.attributes.Active} Active"))
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_top,R.anim.slide_to_bottom)
    }
}
