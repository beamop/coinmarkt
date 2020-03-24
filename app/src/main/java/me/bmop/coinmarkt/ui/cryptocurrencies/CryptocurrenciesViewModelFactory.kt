package me.bmop.coinmarkt.ui.cryptocurrencies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.bmop.coinmarkt.data.repository.CoinMarktRepository

class CryptocurrenciesViewModelFactory(
    private val coinMarktRepository: CoinMarktRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CryptocurrenciesViewModel(coinMarktRepository) as T
    }
}