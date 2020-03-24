package me.bmop.coinmarkt.ui.exchanges

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.bmop.coinmarkt.data.repository.CoinMarktRepository

class ExchangesViewModelFactory(
    private val coinMarktRepository: CoinMarktRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ExchangesViewModel(coinMarktRepository) as T
    }
}