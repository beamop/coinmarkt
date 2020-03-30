package me.bmop.coinmarkt.ui.cryptocurrencies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.bmop.coinmarkt.data.repository.CoinGeckoRepository

class CryptocurrenciesViewModelFactory(
    private val coinGeckoRepository: CoinGeckoRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CryptocurrenciesViewModel(coinGeckoRepository) as T
    }
}