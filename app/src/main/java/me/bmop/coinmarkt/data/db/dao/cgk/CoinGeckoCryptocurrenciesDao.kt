package me.bmop.coinmarkt.data.db.dao.cgk

import androidx.lifecycle.LiveData
import androidx.room.*
import me.bmop.coinmarkt.data.db.entity.cgk.cryptocurrencies.CoinGeckoCryptocurrenciesEntry

@Dao
interface CoinGeckoCryptocurrenciesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertCoinGeckoCryptocurrencies(cryptocurrencies: List<CoinGeckoCryptocurrenciesEntry>)

    @Query(value = "select * from coinmarkt_cgk_cryptocurrency")
    fun getCoinGeckoCryptocurrencies(): LiveData<List<CoinGeckoCryptocurrenciesEntry>>
}