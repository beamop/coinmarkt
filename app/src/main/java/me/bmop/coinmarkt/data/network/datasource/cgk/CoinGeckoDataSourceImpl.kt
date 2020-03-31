package me.bmop.coinmarkt.data.network.datasource.cgk

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.bmop.coinmarkt.data.network.datasource.cgk.CoinGeckoDataSource
import me.bmop.coinmarkt.data.network.response.cgk.CoinGeckoCryptocurrenciesResponse
import me.bmop.coinmarkt.data.network.response.cgk.CoinGeckoExchangesResponse
import me.bmop.coinmarkt.internal.NoConnectivityException
import me.bmop.coinmarkt.service.CoinGeckoApiService

class CoinGeckoDataSourceImpl(
    private val coinGeckoApiService: CoinGeckoApiService
) : CoinGeckoDataSource {

    private val _downloadedCoinGeckoCryptocurrencies = MutableLiveData<CoinGeckoCryptocurrenciesResponse>()
    private val _downloadedCoinGeckoExchanges = MutableLiveData<CoinGeckoExchangesResponse>()

    override val downloadedCoinGeckoCryptocurrencies: LiveData<CoinGeckoCryptocurrenciesResponse>
        get() =  _downloadedCoinGeckoCryptocurrencies

    override val downloadedCoinGeckoExchanges: LiveData<CoinGeckoExchangesResponse>
        get() = _downloadedCoinGeckoExchanges

    override suspend fun fetchCoinGeckoCryptocurrencies() {
        try {
            val fetchedCoinGeckoCryptocurrencies = coinGeckoApiService
                .getCoinGeckoCryptocurrencies("usd", true, "1h, 24h, 7d, 14d, 30d, 200d, 1y")
                .await()

            _downloadedCoinGeckoCryptocurrencies.postValue(fetchedCoinGeckoCryptocurrencies)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "Unable to get cryptocurrencies from the CGK API", e)
        }
    }

    override suspend fun fetchCoinGeckoExchanges() {
        try {
            val fetchedCoinGeckoExchanges = coinGeckoApiService
                .getCoinGeckoExchanges()
                .await()

            _downloadedCoinGeckoExchanges.postValue(fetchedCoinGeckoExchanges)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "Unable to get exchanges from the CMC API", e)
        }
    }
}