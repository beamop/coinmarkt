package me.bmop.coinmarkt.data.network

import androidx.lifecycle.LiveData
import me.bmop.coinmarkt.data.network.response.CoinMarketCapCryptocurrenciesResponse
import me.bmop.coinmarkt.data.network.response.CoinMarketCapExchangesResponse

interface CoinMarktDataSource {
    val downloadedCoinMarketCapListingsCryptocurrencies: LiveData<CoinMarketCapCryptocurrenciesResponse>
    val downloadedCoinMarketCapExchanges: LiveData<CoinMarketCapExchangesResponse>

    suspend fun fetchCoinMarketCapCryptocurrency()

    suspend fun fetchCoinMarketCapExchanges()
}