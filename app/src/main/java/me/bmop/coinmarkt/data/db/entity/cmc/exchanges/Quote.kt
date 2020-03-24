package me.bmop.coinmarkt.data.db.entity.cmc.exchanges

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("USD")
    @Embedded(prefix = "usd_")
    val usd: Usd
)