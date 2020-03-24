package me.bmop.coinmarkt.data.db.entity.cmc.exchanges

import com.google.gson.annotations.SerializedName

data class Usd(
    @SerializedName("volume_24h")
    val volume24h: Double,
    @SerializedName("volume_24h_adjusted")
    val volume24hAdjusted: Double,
    @SerializedName("volume_7d")
    val volume7d: Double,
    @SerializedName("volume_30d")
    val volume30d: Double,
    @SerializedName("percent_change_volume_24h")
    val percentChangeVolume24h: Double,
    @SerializedName("percent_change_volume_7d")
    val percentChangeVolume7d: Double,
    @SerializedName("percent_change_volume_30d")
    val percentChangeVolume30d: Double
)