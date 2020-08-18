package me.bmop.coinmarkt.data.network.datasource.cc

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.bmop.coinmarkt.data.network.response.cc.CryptoControlNewsResponse
import me.bmop.coinmarkt.internal.NoConnectivityException
import me.bmop.coinmarkt.service.CryptoControlApiService

class CryptoControlDataSourceImpl(
    private val cryptoControlApiService: CryptoControlApiService
) : CryptoControlDataSource {

    private val _downloadedCryptoControlNews = MutableLiveData<CryptoControlNewsResponse>()

    override val downloadedCryptoControlNews: LiveData<CryptoControlNewsResponse>
        get() = _downloadedCryptoControlNews

    override suspend fun fetchCryptoControlNews() {
        try {
            val fetchedCryptoControlNews = cryptoControlApiService
                .getNews()
                .await()

            _downloadedCryptoControlNews.postValue(fetchedCryptoControlNews)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "Unable to get top news from CC API", e)
        }
    }
}