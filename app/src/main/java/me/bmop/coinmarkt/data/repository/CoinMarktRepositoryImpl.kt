package me.bmop.coinmarkt.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.bmop.coinmarkt.data.db.CoinMarktCryptocurrenciesDao
import me.bmop.coinmarkt.data.db.CoinMarktExchangesDao
import me.bmop.coinmarkt.data.db.entity.cmc.cryptocurrencies.CoinMarketCapCryptocurrenciesEntry
import me.bmop.coinmarkt.data.db.entity.cmc.exchanges.CoinMarketCapExchangesEntry
import me.bmop.coinmarkt.data.network.CoinMarktDataSource
import me.bmop.coinmarkt.data.network.response.CoinMarketCapCryptocurrenciesResponse
import me.bmop.coinmarkt.data.network.response.CoinMarketCapExchangesResponse
import org.threeten.bp.ZonedDateTime

class CoinMarktRepositoryImpl(
    private val coinMarktCryptocurrenciesDao: CoinMarktCryptocurrenciesDao,
    private val coinMarktExchangesDao: CoinMarktExchangesDao,
    private val coinMarktDataSource: CoinMarktDataSource
) : CoinMarktRepository {

    init {
        coinMarktDataSource.downloadedCoinMarketCapListingsCryptocurrencies.observeForever { newCryptocurrencies ->
            persistFetchedCryptocurrencies(newCryptocurrencies)
        }

        coinMarktDataSource.downloadedCoinMarketCapExchanges.observeForever { newExchanges ->
            persistFetchedExchanges(newExchanges)
        }
    }

    override suspend fun getCryptocurrencies(): LiveData<List<CoinMarketCapCryptocurrenciesEntry>> {
        return withContext(Dispatchers.IO) {
            initCryptocurrenciesData()
            return@withContext coinMarktCryptocurrenciesDao.getCoinMarketCapCryptocurrencies()
        }
    }

    private fun persistFetchedCryptocurrencies(fetchedCryptocurrencies: CoinMarketCapCryptocurrenciesResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            coinMarktCryptocurrenciesDao.upsertCoinMarketCapCryptocurrencies(fetchedCryptocurrencies.data)
        }
    }

    private suspend fun initCryptocurrenciesData() {
        if (isFetchNeeded(ZonedDateTime.now().minusMinutes(10)))
            fetchCryptocurrencies()
    }

    private suspend fun fetchCryptocurrencies() {
        coinMarktDataSource.fetchCoinMarketCapCryptocurrency(
            1,
            10,
            "USD"
        )
    }

    override suspend fun getExchanges(): LiveData<List<CoinMarketCapExchangesEntry>> {
        return withContext(Dispatchers.IO) {
            initExchangesData()
            return@withContext coinMarktExchangesDao.getCoinMarketCapExchanges()
        }
    }

    private fun persistFetchedExchanges(fetchedExchanges: CoinMarketCapExchangesResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            coinMarktExchangesDao.upsertCoinMarketCapExchanges(fetchedExchanges.data)
        }
    }

    private suspend fun initExchangesData() {
        if (isFetchNeeded(ZonedDateTime.now().minusMinutes(10)))
            fetchExchanges()
    }

    private suspend fun fetchExchanges() {
        coinMarktDataSource.fetchCoinMarketCapExchanges()
    }

    private fun isFetchNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val fiveMinutesAgo = ZonedDateTime.now().minusMinutes(5)
        return lastFetchTime.isBefore(fiveMinutesAgo)
    }
}