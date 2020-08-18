package me.bmop.coinmarkt.data.db.entity.cgk.exchanges

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "coinmarkt_cgk_exchanges")
data class CoinGeckoExchangesEntry(
    val id: String,
    val name: String,
    @SerializedName("year_established")
    val yearEstablished: Int? = 0,
    val country: String? = "Currently no country",
    val description: String? = "Currently no description",
    val url: String,
    val image: String,
    @SerializedName("has_trading_incentive")
    val hasTradingIncentive: Boolean,
    @SerializedName("trust_score")
    val trustScore: Int,
    @SerializedName("trust_score_rank")
    val trustScoreRank: Int,
    @SerializedName("trade_volume_24h_btc")
    val tradeVolume24hBtc: Double,
    @SerializedName("trade_volume_24h_btc_normalized")
    val tradeVolume24hBtcNormalized: Double
) {
    @PrimaryKey(autoGenerate = true)
    var exchangeId: Int = 0
}