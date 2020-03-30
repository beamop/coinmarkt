package me.bmop.coinmarkt.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import me.bmop.coinmarkt.data.db.entity.cgk.cryptocurrencies.CoinGeckoCryptocurrenciesEntry

@Dao
interface CoinMarktCryptocurrenciesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertCoinGeckoCryptocurrencies(coin: List<CoinGeckoCryptocurrenciesEntry>)

    @Query(value = "select * from coinmarkt_cgk_cryptocurrency")
    fun getCoinGeckoCryptocurrencies(): LiveData<List<CoinGeckoCryptocurrenciesEntry>>
}