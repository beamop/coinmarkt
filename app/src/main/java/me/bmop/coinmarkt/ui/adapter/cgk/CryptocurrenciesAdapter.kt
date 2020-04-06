package me.bmop.coinmarkt.ui.adapter.cgk

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.robinhood.spark.SparkView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cryptocurrency_item.view.*
import listen
import me.bmop.coinmarkt.R
import me.bmop.coinmarkt.data.db.entity.cgk.cryptocurrencies.CoinGeckoCryptocurrenciesEntry
import me.bmop.coinmarkt.ui.activity.CryptocurrencyInfoActivity

class CryptocurrenciesAdapter(
    private val cryptocurrenciesList: List<CoinGeckoCryptocurrenciesEntry>
) : RecyclerView.Adapter<CryptocurrenciesAdapter.CryptocurrenciesViewHolder>() {

    private val greenColor = Color.rgb(0, 158, 115)
    private val redColor = Color.rgb(217, 64, 64)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptocurrenciesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cryptocurrency_item, parent, false)

        return CryptocurrenciesViewHolder(
            itemView = itemView
        ).listen { position, type ->
            val item = cryptocurrenciesList[position]
            val intent = Intent(itemView.context, CryptocurrencyInfoActivity::class.java)
            intent.putExtra(CRYPTOCURRENCY, item)

            startActivity(itemView.context, intent, null)
        }
    }

    override fun onBindViewHolder(holder: CryptocurrenciesViewHolder, position: Int) {
        val currentItem = cryptocurrenciesList[position]

        Picasso.get()
            .load(currentItem.image)
            .into(holder.cryptocurrencyImage)

        holder.cryptocurrencyChange24h.setTextColor(if (currentItem.priceChangePercentage24h < 0) redColor else greenColor)

        holder.cryptocurrencySparklineIn7d.adapter =
            SparkViewAdapter(currentItem.sparklineIn7d.price)
        holder.cryptocurrencySparklineIn7d.lineColor = if (currentItem.priceChangePercentage24h < 0) redColor else greenColor

        holder.cryptocurrencyName.text = currentItem.name
        holder.cryptocurrencySymbol.text = currentItem.symbol.toUpperCase()
        holder.cryptocurrencyChange24h.text = currentItem.priceChangePercentage24h.toString().plus("%")
        holder.cryptocurrencyPrice.text = "$".plus(currentItem.currentPrice)
    }

    override fun getItemCount() = cryptocurrenciesList.size

    class CryptocurrenciesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cryptocurrencyImage: ImageView = itemView.cryptocurrency_image
        val cryptocurrencyName: TextView = itemView.cryptocurrency_name
        val cryptocurrencySymbol: TextView = itemView.cryptocurrency_symbol
        val cryptocurrencyChange24h: TextView = itemView.cryptocurrency_change
        val cryptocurrencySparklineIn7d: SparkView = itemView.cryptocurrency_sparkview
        val cryptocurrencyPrice: TextView = itemView.cryptocurrency_price
    }

    companion object {
        const val CRYPTOCURRENCY = "cryptocurrency"
    }

}