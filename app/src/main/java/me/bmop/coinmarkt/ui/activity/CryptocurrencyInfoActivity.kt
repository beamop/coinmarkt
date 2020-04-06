package me.bmop.coinmarkt.ui.activity

import android.animation.Animator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.robinhood.spark.SparkView.OnScrubListener
import com.robinhood.spark.animation.MorphSparkAnimator
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cryptocurrency_info.*
import me.bmop.coinmarkt.R
import me.bmop.coinmarkt.data.db.entity.cgk.cryptocurrencies.CoinGeckoCryptocurrenciesEntry
import me.bmop.coinmarkt.ui.adapter.cgk.CryptocurrenciesAdapter
import me.bmop.coinmarkt.ui.adapter.cgk.SparkViewAdapter
import java.text.NumberFormat
import java.util.*


class CryptocurrencyInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cryptocurrency_info)

        intent?.let {
            val cryptocurrencyInfo = intent.extras?.getParcelable(CryptocurrenciesAdapter.CRYPTOCURRENCY) as CoinGeckoCryptocurrenciesEntry?
            val locale = Locale.FRANCE

            if (cryptocurrencyInfo != null) {
                Picasso.get()
                    .load(cryptocurrencyInfo.image)
                    .into(cryptocurrency_image)

                cryptocurrency_sparkview.adapter = SparkViewAdapter(cryptocurrencyInfo.sparklineIn7d.price)
                cryptocurrency_sparkview.scrubListener = OnScrubListener { value ->
                    if (value !== null) cryptocurrency_spark_price.text = "$".plus(value)
                }
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