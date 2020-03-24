package me.bmop.coinmarkt.data.db.entity.cmc.exchanges

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "coinmarkt_cmc_exchanges")
data class CoinMarketCapExchangesEntry(
    val id: Int,
    val name: String,
    val slug: String,
    @SerializedName("num_market_pairs")
    val numMarketPairs: Int,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @Embedded(prefix = "quote_")
    val quote: Quote
) {
    @PrimaryKey(autoGenerate = true)
    var exchangeId: Int = 0
}