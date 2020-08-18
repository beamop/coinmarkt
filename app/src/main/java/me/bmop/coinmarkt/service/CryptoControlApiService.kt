package me.bmop.coinmarkt.service

import kotlinx.coroutines.Deferred
import me.bmop.coinmarkt.data.network.ConnectivityInterceptor
import me.bmop.coinmarkt.data.network.response.cc.CryptoControlNewsResponse
import retrofit2.http.GET

interface CryptoControlApiService {

    @GET("public/news")
    fun getNews() : Deferred<CryptoControlNewsResponse>

    companion object {
        operator fun invoke(
            apiConfiguration: ApiConfiguration,
            connectivityInterceptor: ConnectivityInterceptor
        ): CryptoControlApiService {
            val authorization = mapOf("headerKey" to "noauth", "headerValue" to "noauth")

            return apiConfiguration
                .getConfiguration(CC_API_ENDPOINT, authorization, connectivityInterceptor)
                .create(CryptoControlApiService::class.java)
        }
    }

}
