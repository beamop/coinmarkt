package me.bmop.coinmarkt.data.network

import androidx.lifecycle.LiveData
import me.bmop.coinmarkt.data.network.response.cgk.CoinGeckoCryptocurrenciesResponse
import me.bmop.coinmarkt.data.network.response.cgk.CoinGeckoExchangesResponse

interface CoinGeckoDataSource {
    val downloadedCoinGeckoCryptocurrencies: LiveData<CoinGeckoCryptocurrenciesResponse>
    val downloadedCoinGeckoExchanges: LiveData<CoinGeckoExchangesResponse>

    suspend fun fetchCoinGeckoCryptocurrencies()

    suspend fun fetchCoinGeckoExchanges()
}