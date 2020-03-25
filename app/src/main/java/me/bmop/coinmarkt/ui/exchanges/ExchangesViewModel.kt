package me.bmop.coinmarkt.ui.exchanges

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import me.bmop.coinmarkt.data.db.entity.cmc.exchanges.CoinMarketCapExchangesEntry
import me.bmop.coinmarkt.data.repository.CoinMarktRepository
import me.bmop.coinmarkt.internal.lazyDeferred

class ExchangesViewModel(
    private val coinMarktRepository: CoinMarktRepository
) : ViewModel() {

    suspend fun getExchanges(): LiveData<List<CoinMarketCapExchangesEntry>> {
        return coinMarktRepository.getExchanges()
    }

}