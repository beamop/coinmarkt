package me.bmop.coinmarkt.service

import kotlinx.coroutines.Deferred
import me.bmop.coinmarkt.data.network.ConnectivityInterceptor
import me.bmop.coinmarkt.data.network.response.CoinMarketCapCryptocurrenciesResponse
import me.bmop.coinmarkt.data.network.response.CoinMarketCapExchangesResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap
import java.util.*

interface CoinMarketCapApiService {

    @GET("cryptocurrency/listings/latest")
    fun getCoinMarketCapCryptocurrencies(): Deferred<CoinMarketCapCryptocurrenciesResponse>

    @GET("exchange/listings/latest")
    fun getCoinMarketCapExchanges(): Deferred<CoinMarketCapExchangesResponse>

    companion object {
        operator fun invoke(
            apiConfiguration: ApiConfiguration,
            connectivityInterceptor: ConnectivityInterceptor
        ): CoinMarketCapApiService {
            val authorization = mapOf("headerKey" to "X-CMC_PRO_API_KEY", "headerValue" to CMC_API_KEY)

            return apiConfiguration
                .getConfiguration(CMC_API_ENDPOINT, authorization, connectivityInterceptor)
                .create(CoinMarketCapApiService::class.java)
        }
    }
}