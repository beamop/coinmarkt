package me.bmop.coinmarkt.data.repository

import androidx.lifecycle.LiveData
import me.bmop.coinmarkt.data.db.entity.cmc.cryptocurrencies.CoinMarketCapCryptocurrenciesEntry
import me.bmop.coinmarkt.data.db.entity.cmc.exchanges.CoinMarketCapExchangesEntry

interface CoinMarktRepository {
    suspend fun getCryptocurrencies(): LiveData<List<CoinMarketCapCryptocurrenciesEntry>>

    suspend fun getExchanges(): LiveData<List<CoinMarketCapExchangesEntry>>
}