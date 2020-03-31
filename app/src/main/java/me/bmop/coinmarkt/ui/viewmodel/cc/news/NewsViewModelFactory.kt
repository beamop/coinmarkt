package me.bmop.coinmarkt.ui.viewmodel.cc.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.bmop.coinmarkt.data.repository.cc.CryptoControlRepository

class NewsViewModelFactory(
    private val cryptoControlRepository: CryptoControlRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(
            cryptoControlRepository
        ) as T
    }

}