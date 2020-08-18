package me.bmop.coinmarkt.data.network.datasource.cc

import androidx.lifecycle.LiveData
import me.bmop.coinmarkt.data.network.response.cc.CryptoControlNewsResponse

interface CryptoControlDataSource {
    val downloadedCryptoControlNews: LiveData<CryptoControlNewsResponse>

    suspend fun fetchCryptoControlNews()

}