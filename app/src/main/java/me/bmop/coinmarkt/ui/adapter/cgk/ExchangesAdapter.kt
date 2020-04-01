package me.bmop.coinmarkt.ui.adapter.cgk

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.exchange_item.view.*
import listen
import me.bmop.coinmarkt.R
import me.bmop.coinmarkt.data.db.entity.cgk.exchanges.CoinGeckoExchangesEntry

class ExchangesAdapter(
    private val exchangesList: List<CoinGeckoExchangesEntry>
) : RecyclerView.Adapter<ExchangesAdapter.ExchangesViewHolder>() {

    private val greenColor = Color.rgb(0, 158, 115)
    private val redColor = Color.rgb(217, 64, 64)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.exchange_item, parent, false)

        return ExchangesViewHolder(
            itemView = itemView
        ).listen { position, type ->
            val item = exchangesList[position]
            val exchangeUrl = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))

            startActivity(itemView.context, exchangeUrl, null)
        }
    }

    override fun onBindViewHolder(holder: ExchangesViewHolder, position: Int) {
        val currentItem = exchangesList[position]

        Picasso.get()
            .load(currentItem.image)
            .into(holder.exchangeImage)

        holder.exchangeChange24h.setTextColor(if (currentItem.tradeVolume24hBtc < 0) redColor else greenColor)

        if (currentItem.trustScore >= 8) holder.exchangeIsRecommended.visibility = View.VISIBLE else holder.exchangeIsRecommended.visibility = View.GONE

        holder.exchangeChange24h.text = currentItem.tradeVolume24hBtcNormalized.toString().plus("%")
        holder.exchangeName.text = currentItem.name
        holder.exchangeTrustScore.text = currentItem.trustScore.toString().plus("/10")

    }

    override fun getItemCount() = exchangesList.size

    class ExchangesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val exchangeImage: ImageView = itemView.exchange_image
        val exchangeName: TextView = itemView.exchange_name
        val exchangeTrustScore: TextView = itemView.exchange_trustScore
        val exchangeChange24h: TextView = itemView.exchange_change24h
        //val exchangeSparklines: ImageView = itemView.exchange_sparklines
        val exchangeIsRecommended: ImageView = itemView.exchange_isRecommended
    }

}