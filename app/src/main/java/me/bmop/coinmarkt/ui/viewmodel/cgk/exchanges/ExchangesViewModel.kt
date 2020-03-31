package me.bmop.coinmarkt.ui.viewmodel.cgk.exchanges

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import me.bmop.coinmarkt.data.db.entity.cgk.exchanges.CoinGeckoExchangesEntry
import me.bmop.coinmarkt.data.repository.cgk.CoinGeckoRepository

class ExchangesViewModel(
    private val coinGeckoRepository: CoinGeckoRepository
) : ViewModel() {

    suspend fun getExchanges(): LiveData<List<CoinGeckoExchangesEntry>> {
        return coinGeckoRepository.getExchanges()
    }

}