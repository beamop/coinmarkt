package me.bmop.coinmarkt.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.ColorFilterTransformation
import kotlinx.android.synthetic.main.cryptocurrency_item.view.*
import me.bmop.coinmarkt.R
import me.bmop.coinmarkt.data.db.entity.cmc.cryptocurrencies.CoinMarketCapCryptocurrenciesEntry
import kotlin.math.round

const val CMC_CRYPTOCURRENCY_STATIC_ENDPOINT = "https://s2.coinmarketcap.com/static/img/coins/64x64/"
const val CMC_CRYPTOCURRENCY_GENERATED_SPARKLINES_ENDPOINT = "https://s2.coinmarketcap.com/generated/sparklines/web/"

class CryptocurrenciesAdapter(
    private val cryptocurrenciesList: List<CoinMarketCapCryptocurrenciesEntry>
) : RecyclerView.Adapter<CryptocurrenciesAdapter.CryptocurrenciesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptocurrenciesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cryptocurrency_item, parent, false)

        return CryptocurrenciesViewHolder(itemView = itemView)
    }

    override fun onBindViewHolder(holder: CryptocurrenciesViewHolder, position: Int) {
        val currentItem = cryptocurrenciesList[position]
        val coinImageUrl = CMC_CRYPTOCURRENCY_STATIC_ENDPOINT + "${currentItem.id}.png"
        val coinSparklinesUrl = CMC_CRYPTOCURRENCY_GENERATED_SPARKLINES_ENDPOINT + "7d/usd/${currentItem.id}.png"

        Picasso.get()
            .load(coinImageUrl)
            .into(holder.cryptocurrencyImage)
        Picasso.get()
            .load(coinSparklinesUrl)
            .transform(ColorFilterTransformation(Color.rgb(0, 158, 115)))
            .into(holder.cryptocurrencySparklines)

        holder.cryptocurrencyName.text = currentItem.name
        holder.cryptocurrencySymbol.text = currentItem.symbol

        if (currentItem.quote.usd.percentChange24h < 0) {
            holder.cryptocurrencyChange24h.setTextColor(Color.rgb(217, 64, 64))
        } else {
            holder.cryptocurrencyChange24h.setTextColor(Color.rgb(0, 158, 115))
        }

        holder.cryptocurrencyChange24h.text = currentItem.quote.usd.percentChange24h.toString().plus("%")

        holder.cryptocurrencyPrice.text = "$".plus(currentItem.quote.usd.price.round(3))
    }

    override fun getItemCount() = cryptocurrenciesList.size

    class CryptocurrenciesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cryptocurrencyImage: ImageView = itemView.cryptocurrency_image
        val cryptocurrencyName: TextView = itemView.cryptocurrency_name
        val cryptocurrencySymbol: TextView = itemView.cryptocurrency_symbol
        val cryptocurrencyChange24h: TextView = itemView.cryptocurrency_change
        val cryptocurrencySparklines: ImageView = itemView.cryptocurrency_sparklines
        val cryptocurrencyPrice: TextView = itemView.cryptocurrency_price
    }

}

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}