package me.bmop.coinmarkt.data.repository.cc

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.bmop.coinmarkt.data.db.dao.cc.CryptoControlNewsDao
import me.bmop.coinmarkt.data.db.entity.cc.news.CryptoControlNewsEntry
import me.bmop.coinmarkt.data.network.datasource.cc.CryptoControlDataSource
import me.bmop.coinmarkt.data.network.response.cc.CryptoControlNewsResponse
import me.bmop.coinmarkt.data.repository.cc.CryptoControlRepository

class CryptoControlRepositoryImpl(
    private val cryptoControlNewsDao: CryptoControlNewsDao,
    private val cryptoControlDataSource: CryptoControlDataSource
) : CryptoControlRepository {

    init {
        cryptoControlDataSource.downloadedCryptoControlNews.observeForever { newNews ->
            persistFetchedNews(newNews)
        }
    }

    override suspend fun getNews(): LiveData<List<CryptoControlNewsEntry>> {
        return withContext(Dispatchers.IO) {
            cryptoControlDataSource.fetchCryptoControlNews()
            return@withContext cryptoControlNewsDao.getCryptoControlNews()
        }
    }

    private fun persistFetchedNews(fetchedNews: CryptoControlNewsResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            cryptoControlNewsDao.upsertCryptoControlNews(fetchedNews)
        }
    }
}