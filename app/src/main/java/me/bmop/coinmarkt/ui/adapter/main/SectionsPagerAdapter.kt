@file:Suppress("DEPRECATION")

package me.bmop.coinmarkt.ui.adapter.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import me.bmop.coinmarkt.R
import me.bmop.coinmarkt.ui.fragment.cgk.exchanges.ExchangesFragment
import me.bmop.coinmarkt.ui.fragment.cgk.cryptocurrencies.CryptocurrenciesFragment

private val TAB_TITLES = arrayOf(
        R.string.tab_listings,
        R.string.tab_exchange
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        var fragment = Fragment()

        when (position) {
            0 -> fragment = CryptocurrenciesFragment.newInstance()
            1 -> fragment = ExchangesFragment.newInstance()
        }

        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int = 2
}