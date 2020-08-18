package me.bmop.coinmarkt.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import me.bmop.coinmarkt.data.db.entity.cc.news.CryptoControlNewsEntry
import me.bmop.coinmarkt.data.repository.cc.CryptoControlRepository

class NewsViewModel(
    private val cryptoControlRepository: CryptoControlRepository
) : ViewModel() {

    suspend fun getNews(): LiveData<List<CryptoControlNewsEntry>> {
        return cryptoControlRepository.getNews()
    }

}