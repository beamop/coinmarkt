package me.bmop.coinmarkt.ui.cryptocurrencies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import me.bmop.coinmarkt.data.db.entity.cmc.cryptocurrencies.CoinMarketCapCryptocurrenciesEntry
import me.bmop.coinmarkt.data.repository.CoinMarktRepository
import me.bmop.coinmarkt.internal.lazyDeferred

class CryptocurrenciesViewModel(
    private val coinMarktRepository: CoinMarktRepository
) : ViewModel() {

    suspend fun getCryptocurrencies(): LiveData<List<CoinMarketCapCryptocurrenciesEntry>> {
        return coinMarktRepository.getCryptocurrencies()
    }

}