package me.bmop.coinmarkt.data.db.entity.cgk.cryptocurrencies

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "coinmarkt_cgk_cryptocurrency")
data class CoinGeckoCryptocurrenciesEntry(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    @SerializedName("current_price")
    val currentPrice: Double,
    @SerializedName("market_cap")
    val marketCap: Double,
    @SerializedName("market_cap_rank")
    val marketCapRank: Int,
    @SerializedName("total_volume")
    val totalVolume: Double,
    @SerializedName("high_24h")
    val high24h: Double,
    @SerializedName("low_24h")
    val low24h: Double,
    @SerializedName("price_change_24h")
    val priceChange24h: Double,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Double,
    @SerializedName("market_cap_change_24h")
    val marketCapChange24h: Double,
    @SerializedName("market_cap_change_percentage_24h")
    val marketCapChangePercentage24h: Double,
    @SerializedName("circulating_supply")
    val circulatingSupply: Double,
    @SerializedName("total_supply")
    val totalSupply: Double,
    val ath: Double,
    @SerializedName("ath_change_percentage")
    val athChangePercentage: Double,
    @SerializedName("ath_date")
    val athDate: String,
//    @Embedded(prefix = "roi_")
//    val roi: Roi,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("sparkline_in_7d")
    @Embedded(prefix = "sparklineIn7d_")
    val sparklineIn7d: SparklineIn7d
) {
    @PrimaryKey(autoGenerate = true)
    var cryptocurrencyId: Int = 0
}