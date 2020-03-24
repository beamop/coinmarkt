package me.bmop.coinmarkt.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.bmop.coinmarkt.data.db.entity.cmc.exchanges.CoinMarketCapExchangesEntry

@Dao
interface CoinMarktExchangesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertCoinMarketCapExchanges(exchange: List<CoinMarketCapExchangesEntry>)

    @Query(value = "select * from coinmarkt_cmc_exchanges")
    fun getCoinMarketCapExchanges(): LiveData<List<CoinMarketCapExchangesEntry>>
}