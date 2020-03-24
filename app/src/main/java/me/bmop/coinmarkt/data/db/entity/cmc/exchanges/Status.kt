package me.bmop.coinmarkt.data.db.entity.cmc.exchanges

import com.google.gson.annotations.SerializedName

data class Status(
    val timestamp: String,
    @SerializedName("error_code")
    val errorCode: Int,
    @SerializedName("error_message")
    val errorMessage: Any,
    val elapsed: Int,
    @SerializedName("credit_count")
    val creditCount: Int,
    val notice: String
)