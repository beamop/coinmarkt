package me.bmop.coinmarkt.data.repository

import androidx.lifecycle.LiveData
import me.bmop.coinmarkt.data.db.entity.cgk.cryptocurrencies.CoinGeckoCryptocurrenciesEntry
import me.bmop.coinmarkt.data.db.entity.cgk.exchanges.CoinGeckoExchangesEntry

interface CoinGeckoRepository {
    suspend fun getCryptocurrencies(): LiveData<List<CoinGeckoCryptocurrenciesEntry>>

    suspend fun getExchanges(): LiveData<List<CoinGeckoExchangesEntry>>
}