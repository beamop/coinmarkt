package me.bmop.coinmarkt.ui.cryptocurrencies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cryptocurrency_info.*
import me.bmop.coinmarkt.R
import me.bmop.coinmarkt.data.db.entity.cgk.cryptocurrencies.CoinGeckoCryptocurrenciesEntry
import me.bmop.coinmarkt.ui.adapter.cryptocurrencies.CryptocurrenciesAdapter
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class CryptocurrencyInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cryptocurrency_info)

        intent?.let {
            val cryptocurrencyInfo = intent.extras?.getParcelable(CryptocurrenciesAdapter.CRYPTOCURRENCY) as CoinGeckoCryptocurrenciesEntry?
            val locale = Locale.FRANCE
            val entries = ArrayList<Entry>()

            if (cryptocurrencyInfo != null) {
                Picasso.get()
                    .load(cryptocurrencyInfo.image)
                    .into(cryptocurrency_image)

                cryptocurrencyInfo.sparklineIn7d.price.forEachIndexed { index, d ->
                    entries.add(Entry(index.toFloat(), d.toFloat()))
                }
                val dataSet = LineDataSet(entries, cryptocurrencyInfo.name.plus(" prices"))
                dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
                dataSet.setDrawFilled(true)
                dataSet.setDrawCircles(false)
                dataSet.lineWidth = 2f
                cryptocurrency_chart.data = LineData(dataSet)
                cryptocurrency_chart.invalidate()
                cryptocurrency_low_24h.text = "$".plus(cryptocurrencyInfo.low24h)
                cryptocurrency_high_24h.text = "$".plus(cryptocurrencyInfo.high24h)
                cryptocurrency_name.text = cryptocurrencyInfo.name.plus(" (".plus(cryptocurrencyInfo.symbol.toUpperCase().plus(")")))
                cryptocurrency_price.text = "$".plus(cryptocurrencyInfo.currentPrice)
                cryptocurrency_rank.text = "#".plus(cryptocurrencyInfo.marketCapRank)
                cryptocurrency_change_percentage_24h.text = cryptocurrencyInfo.priceChangePercentage24h.toString().plus("%")
                cryptocurrency_circulation.text = NumberFormat.getNumberInstance(locale).format(cryptocurrencyInfo.circulatingSupply)
            }
        }
    }

}