package me.bmop.coinmarkt.data.repository.cgk

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.bmop.coinmarkt.data.db.dao.cgk.CoinGeckoCryptocurrenciesDao
import me.bmop.coinmarkt.data.db.dao.cgk.CoinGeckoExchangesDao
import me.bmop.coinmarkt.data.db.entity.cgk.cryptocurrencies.CoinGeckoCryptocurrenciesEntry
import me.bmop.coinmarkt.data.db.entity.cgk.exchanges.CoinGeckoExchangesEntry
import me.bmop.coinmarkt.data.network.datasource.cgk.CoinGeckoDataSource
import me.bmop.coinmarkt.data.network.response.cgk.CoinGeckoCryptocurrenciesResponse
import me.bmop.coinmarkt.data.network.response.cgk.CoinGeckoExchangesResponse
import me.bmop.coinmarkt.data.repository.cgk.CoinGeckoRepository

class CoinGeckoRepositoryImpl(
    private val coinGeckoCryptocurrenciesDao: CoinGeckoCryptocurrenciesDao,
    private val coinGeckoExchangesDao: CoinGeckoExchangesDao,
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

    private suspend fun initCryptocurrenciesData() {
        fetchCryptocurrencies()
    }

    private suspend fun initExchangesData() {
        fetchExchanges()
    }

    private suspend fun fetchCryptocurrencies() {
        coinGeckoDataSource.fetchCoinGeckoCryptocurrencies()
    }

    private suspend fun fetchExchanges() {
        coinGeckoDataSource.fetchCoinGeckoExchanges()
    }

    override suspend fun getCryptocurrencies(): LiveData<List<CoinGeckoCryptocurrenciesEntry>> {
        return withContext(Dispatchers.IO) {
            initCryptocurrenciesData()
            return@withContext coinGeckoCryptocurrenciesDao.getCoinGeckoCryptocurrencies()
        }
    }

    private fun persistFetchedCryptocurrencies(fetchedCryptocurrencies: CoinGeckoCryptocurrenciesResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            coinGeckoCryptocurrenciesDao.upsertCoinGeckoCryptocurrencies(fetchedCryptocurrencies)
        }
    }

    override suspend fun getExchanges(): LiveData<List<CoinGeckoExchangesEntry>> {
        return withContext(Dispatchers.IO) {
            initExchangesData()
            return@withContext coinGeckoExchangesDao.getCoinGeckoExchanges()
        }
    }

    private fun persistFetchedExchanges(fetchedExchanges: CoinGeckoExchangesResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            coinGeckoExchangesDao.upsertCoinGeckoExchanges(fetchedExchanges)
        }
    }
}