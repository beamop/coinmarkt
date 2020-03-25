@file:Suppress("DEPRECATION")

package me.bmop.coinmarkt.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import me.bmop.coinmarkt.R
import me.bmop.coinmarkt.ui.adapter.SectionsPagerAdapter
import me.bmop.coinmarkt.ui.base.ScopeFragment
import me.bmop.coinmarkt.ui.cryptocurrencies.CryptocurrenciesFragment
import me.bmop.coinmarkt.ui.exchanges.ExchangesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter =
            SectionsPagerAdapter(
                this,
                supportFragmentManager
            )
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }

}