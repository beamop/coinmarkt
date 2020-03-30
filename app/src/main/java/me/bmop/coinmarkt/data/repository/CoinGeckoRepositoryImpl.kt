package me.bmop.coinmarkt.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.bmop.coinmarkt.data.db.CoinMarktCryptocurrenciesDao
import me.bmop.coinmarkt.data.db.CoinMarktExchangesDao
import me.bmop.coinmarkt.data.db.entity.cgk.cryptocurrencies.CoinGeckoCryptocurrenciesEntry
import me.bmop.coinmarkt.data.db.entity.cgk.exchanges.CoinGeckoExchangesEntry
import me.bmop.coinmarkt.data.network.CoinGeckoDataSource
import me.bmop.coinmarkt.data.network.response.cgk.CoinGeckoCryptocurrenciesResponse
import me.bmop.coinmarkt.data.network.response.cgk.CoinGeckoExchangesResponse

class CoinGeckoRepositoryImpl(
    private val coinMarktCryptocurrenciesDao: CoinMarktCryptocurrenciesDao,
    private val coinMarktExchangesDao: CoinMarktExchangesDao,
    private val coinGeckoDataSource: CoinGeckoDataSource
) : CoinGeckoRepository {

    init {
        coinGeckoDataSource.downloadedCoinGeckoCryptocurrencies.observeForever { newCryptocurrencies ->
            persistFetchedCryptocurrencies(newCryptocurrencies)
        }

        coinGeckoDataSource.downloadedCoinGeckoExchanges.observeForever { newExchanges ->
            persistFetchedExchanges(newExchanges)
        }
    }

    override suspend fun getCryptocurrencies(): LiveData<List<CoinGeckoCryptocurrenciesEntry>> {
        return withContext(Dispatchers.IO) {
            initCryptocurrenciesData()
            return@withContext coinMarktCryptocurrenciesDao.getCoinGeckoCryptocurrencies()
        }
    }

    private fun persistFetchedCryptocurrencies(fetchedCryptocurrencies: CoinGeckoCryptocurrenciesResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            coinMarktCryptocurrenciesDao.upsertCoinGeckoCryptocurrencies(fetchedCryptocurrencies)
        }
    }

    private suspend fun initCryptocurrenciesData() {
        fetchCryptocurrencies()
    }

    private suspend fun fetchCryptocurrencies() {
        coinGeckoDataSource.fetchCoinGeckoCryptocurrencies()
    }

    override suspend fun getExchanges(): LiveData<List<CoinGeckoExchangesEntry>> {
        return withContext(Dispatchers.IO) {
            initExchangesData()
            return@withContext coinMarktExchangesDao.getCoinGeckoExchanges()
        }
    }

    private fun persistFetchedExchanges(fetchedExchanges: CoinGeckoExchangesResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            coinMarktExchangesDao.upsertCoinGeckoExchanges(fetchedExchanges)
        }
    }

    private suspend fun initExchangesData() {
        fetchExchanges()
    }

    private suspend fun fetchExchanges() {
        coinGeckoDataSource.fetchCoinGeckoExchanges()
    }
}