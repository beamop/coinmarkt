package me.bmop.coinmarkt.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.ColorFilterTransformation
import kotlinx.android.synthetic.main.exchange_item.view.*
import me.bmop.coinmarkt.R
import me.bmop.coinmarkt.data.db.entity.cmc.exchanges.CoinMarketCapExchangesEntry

const val CMC_EXCHANGE_STATIC_ENDPOINT = "https://s2.coinmarketcap.com/static/img/exchanges/64x64/"
const val CMC_EXCHANGE_GENERATED_SPARKLINES_ENDPOINT = "https://s2.coinmarketcap.com/generated/sparklines/exchanges/web/"

class ExchangesAdapter(
    private val exchangesList: List<CoinMarketCapExchangesEntry>
) : RecyclerView.Adapter<ExchangesAdapter.ExchangesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.exchange_item, parent, false)

        return ExchangesViewHolder(itemView = itemView)
    }

    override fun onBindViewHolder(holder: ExchangesViewHolder, position: Int) {
        val currentItem = exchangesList[position]
        val coinImageUrl = CMC_EXCHANGE_STATIC_ENDPOINT + "${currentItem.id}.png"
        val coinSparklinesUrl = CMC_EXCHANGE_GENERATED_SPARKLINES_ENDPOINT + "7d/usd/${currentItem.id}.png"

        Picasso.get()
            .load(coinImageUrl)
            .into(holder.exchangeImage)
        Picasso.get()
            .load(coinSparklinesUrl)
            .transform(ColorFilterTransformation(Color.rgb(0, 158, 115)))
            .into(holder.exchangeSparklines)

        holder.exchangeName.text = currentItem.name

        if (currentItem.quote.usd.percentChangeVolume24h < 0) {
            holder.exchangeChange24h.setTextColor(Color.rgb(217, 64, 64))
        } else {
            holder.exchangeChange24h.setTextColor(Color.rgb(0, 158, 115))
        }

        holder.exchangeChange24h.text = currentItem.quote.usd.percentChangeVolume24h.toString().plus("%")

        holder.exchangePrice.text = "$".plus(String.format("%.2fBd", currentItem.quote.usd.volume7d / 1000000000.0))
    }

    override fun getItemCount() = exchangesList.size

    class ExchangesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val exchangeImage: ImageView = itemView.exchange_image
        val exchangeName: TextView = itemView.exchange_name
        val exchangeChange24h: TextView = itemView.exchange_change24h
        val exchangeSparklines: ImageView = itemView.exchange_sparklines
        val exchangePrice: TextView = itemView.exchange_volume
    }

}