package me.bmop.coinmarkt.ui.exchanges

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.bmop.coinmarkt.data.repository.cgk.CoinGeckoRepository

class ExchangesViewModelFactory(
    private val coinGeckoRepository: CoinGeckoRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ExchangesViewModel(
            coinGeckoRepository
        ) as T
    }
}