package me.bmop.coinmarkt.data.repository.cc

import androidx.lifecycle.LiveData
import me.bmop.coinmarkt.data.db.entity.cc.news.CryptoControlNewsEntry

interface CryptoControlRepository {
    suspend fun getNews(): LiveData<List<CryptoControlNewsEntry>>
}