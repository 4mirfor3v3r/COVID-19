package com.amier.covid_19.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amier.covid_19.R
import com.amier.covid_19.view.ui.listcountry.ListCountryFragment

class ListCountry : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_country_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListCountryFragment.newInstance()).commitNow()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_top,R.anim.slide_to_bottom)
    }
}
