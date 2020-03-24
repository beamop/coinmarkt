package me.bmop.coinmarkt.ui.exchanges

import androidx.lifecycle.ViewModel
import me.bmop.coinmarkt.data.repository.CoinMarktRepository
import me.bmop.coinmarkt.internal.lazyDeferred

class ExchangesViewModel(
    private val coinMarktRepository: CoinMarktRepository
) : ViewModel() {

    val coinMarketCapExchanges by lazyDeferred {
        coinMarktRepository.getExchanges()
    }

}