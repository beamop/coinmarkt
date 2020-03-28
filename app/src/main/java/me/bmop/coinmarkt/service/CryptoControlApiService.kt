package me.bmop.coinmarkt.service

import me.bmop.coinmarkt.data.network.ConnectivityInterceptor
import retrofit2.http.GET

interface CryptoControlApiService {

    @GET("public/news")
    fun getTopNews()

    companion object {
        operator fun invoke(
            apiConfiguration: ApiConfiguration,
            connectivityInterceptor: ConnectivityInterceptor
        ): CryptoControlApiService {
            val authorization = mapOf("headerKey" to "X-API-KEY", "headerValue" to CC_API_KEY)

            return apiConfiguration
                .getConfiguration(CC_API_ENDPOINT, authorization, connectivityInterceptor)
                .create(CryptoControlApiService::class.java)
        }
    }

}
