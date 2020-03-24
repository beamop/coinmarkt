package me.bmop.coinmarkt.ui.cryptocurrencies

import androidx.lifecycle.ViewModel
import me.bmop.coinmarkt.data.repository.CoinMarktRepository
import me.bmop.coinmarkt.internal.lazyDeferred

class CryptocurrenciesViewModel(
    private val coinMarktRepository: CoinMarktRepository
) : ViewModel() {

    val coinMarketCapCryptocurrencies by lazyDeferred {
        coinMarktRepository.getCryptocurrencies()
    }

}