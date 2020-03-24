package me.bmop.coinmarkt.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.bmop.coinmarkt.data.network.response.CoinMarketCapCryptocurrenciesResponse
import me.bmop.coinmarkt.data.network.response.CoinMarketCapExchangesResponse
import me.bmop.coinmarkt.internal.NoConnectivityException
import me.bmop.coinmarkt.service.ApiService

class CoinMarktDataSourceImpl(
    private val apiService: ApiService
) : CoinMarktDataSource {

    private val _downloadedCoinMarketCapCryptocurrencies = MutableLiveData<CoinMarketCapCryptocurrenciesResponse>()
    private val _downloadedCoinMarketCapExchanges = MutableLiveData<CoinMarketCapExchangesResponse>()

    override val downloadedCoinMarketCapListingsCryptocurrencies: LiveData<CoinMarketCapCryptocurrenciesResponse>
        get() = _downloadedCoinMarketCapCryptocurrencies

    override val downloadedCoinMarketCapExchanges: LiveData<CoinMarketCapExchangesResponse>
        get() = _downloadedCoinMarketCapExchanges

    override suspend fun fetchCoinMarketCapCryptocurrency(start: Int, limit: Int, currency: String) {
        try {
            val fetchedCoinMarketCapCryptocurrency = apiService
                .getCoinMarketCapListings(start, limit, currency)
                .await()

            _downloadedCoinMarketCapCryptocurrencies.postValue(fetchedCoinMarketCapCryptocurrency)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "Unable to get cryptocurrencies from the CMC API", e)
        }
    }

    override suspend fun fetchCoinMarketCapExchanges() {
        try {
            val fetchedCoinMarketCapExchanges = apiService
                .getCoinMarketCapExchanges()
                .await()

            _downloadedCoinMarketCapExchanges.postValue(fetchedCoinMarketCapExchanges)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "Unable to get exchanges from the CMC API", e)
        }
    }
}