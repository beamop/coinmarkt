package me.bmop.coinmarkt.data.db.entity.cgk.cryptocurrencies

import android.os.Parcel
import android.os.Parcelable
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
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("sparkline_in_7d")
    @Embedded(prefix = "sparklineIn7d_")
    val sparklineIn7d: SparklineIn7d
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var cryptocurrencyId: Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readParcelable(SparklineIn7d::class.java.classLoader)!!
    ) {
        cryptocurrencyId = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(symbol)
        parcel.writeString(name)
        parcel.writeString(image)
        parcel.writeDouble(currentPrice)
        parcel.writeDouble(marketCap)
        parcel.writeInt(marketCapRank)
        parcel.writeDouble(totalVolume)
        parcel.writeDouble(high24h)
        parcel.writeDouble(low24h)
        parcel.writeDouble(priceChange24h)
        parcel.writeDouble(priceChangePercentage24h)
        parcel.writeDouble(marketCapChange24h)
        parcel.writeDouble(marketCapChangePercentage24h)
        parcel.writeDouble(circulatingSupply)
        parcel.writeDouble(totalSupply)
        parcel.writeDouble(ath)
        parcel.writeDouble(athChangePercentage)
        parcel.writeString(athDate)
        parcel.writeString(lastUpdated)
        parcel.writeParcelable(sparklineIn7d, flags)
        parcel.writeInt(cryptocurrencyId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CoinGeckoCryptocurrenciesEntry> {
        override fun createFromParcel(parcel: Parcel): CoinGeckoCryptocurrenciesEntry {
            return CoinGeckoCryptocurrenciesEntry(parcel)
        }

        override fun newArray(size: Int): Array<CoinGeckoCryptocurrenciesEntry?> {
            return arrayOfNulls(size)
        }
    }
}