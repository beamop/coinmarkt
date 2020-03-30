package me.bmop.coinmarkt.ui.cryptocurrencies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import me.bmop.coinmarkt.data.db.entity.cgk.cryptocurrencies.CoinGeckoCryptocurrenciesEntry
import me.bmop.coinmarkt.data.repository.CoinGeckoRepository

class CryptocurrenciesViewModel(
    private val coinGeckoRepository: CoinGeckoRepository
) : ViewModel() {

    suspend fun getCryptocurrencies(): LiveData<List<CoinGeckoCryptocurrenciesEntry>> {
        return coinGeckoRepository.getCryptocurrencies()
    }

}