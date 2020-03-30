package me.bmop.coinmarkt.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.bmop.coinmarkt.data.db.entity.cgk.exchanges.CoinGeckoExchangesEntry

@Dao
interface CoinMarktExchangesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertCoinGeckoExchanges(exchange: List<CoinGeckoExchangesEntry>)

    @Query(value = "select * from coinmarkt_cgk_exchanges")
    fun getCoinGeckoExchanges(): LiveData<List<CoinGeckoExchangesEntry>>
}