package me.bmop.coinmarkt.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.bmop.coinmarkt.data.db.entity.cmc.cryptocurrencies.CoinMarketCapCryptocurrenciesEntry

@Dao
interface CoinMarktCryptocurrenciesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertCoinMarketCapCryptocurrencies(coin: List<CoinMarketCapCryptocurrenciesEntry>)

    @Query(value = "select * from coinmarkt_cmc_cryptocurrency order by cmcRank")
    fun getCoinMarketCapCryptocurrencies(): LiveData<List<CoinMarketCapCryptocurrenciesEntry>>
}