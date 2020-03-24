package me.bmop.coinmarkt.data.db.entity.cmc.cryptocurrencies

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "coinmarkt_cmc_cryptocurrency")
data class CoinMarketCapCryptocurrenciesEntry(
    val name: String,
    val symbol: String,
    val slug: String,
    @SerializedName("num_market_pairs")
    val numMarketPairs: Int,
    @SerializedName("date_added")
    val dateAdded: String,
    @SerializedName("max_supply")
    val maxSupply: Double,
    @SerializedName("circulating_supply")
    val circulatingSupply: Double,
    @SerializedName("total_supply")
    val totalSupply: Double,
    @SerializedName("cmc_rank")
    val cmcRank: Int,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @Embedded(prefix = "quote_")
    val quote: Quote
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}