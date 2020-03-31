package me.bmop.coinmarkt.ui.viewmodel.cgk.cryptocurrencies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import me.bmop.coinmarkt.data.db.entity.cgk.cryptocurrencies.CoinGeckoCryptocurrenciesEntry
import me.bmop.coinmarkt.data.repository.cgk.CoinGeckoRepository

class CryptocurrenciesViewModel(
    private val coinGeckoRepository: CoinGeckoRepository
) : ViewModel() {

    suspend fun getCryptocurrencies(): LiveData<List<CoinGeckoCryptocurrenciesEntry>> {
        return coinGeckoRepository.getCryptocurrencies()
    }

}