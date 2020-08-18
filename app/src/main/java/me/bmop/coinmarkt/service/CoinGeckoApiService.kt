package me.bmop.coinmarkt.service

import kotlinx.coroutines.Deferred
import me.bmop.coinmarkt.data.network.ConnectivityInterceptor
import me.bmop.coinmarkt.data.network.response.cgk.CoinGeckoCryptocurrenciesResponse
import me.bmop.coinmarkt.data.network.response.cgk.CoinGeckoExchangesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinGeckoApiService {

    @GET("coins/markets")
    fun getCoinGeckoCryptocurrencies(
        @Query("vs_currency") currency: String,
        @Query("sparkline") sparkline: Boolean,
        @Query("price_change_percentage") priceChangePercentage: String
    ): Deferred<CoinGeckoCryptocurrenciesResponse>

    @GET("exchanges")
    fun getCoinGeckoExchanges() : Deferred<CoinGeckoExchangesResponse>

    companion object {
        operator fun invoke(
            apiConfiguration: ApiConfiguration,
            connectivityInterceptor: ConnectivityInterceptor
        ): CoinGeckoApiService {
            val authorization = mapOf("headerKey" to "noauth", "headerValue" to "noauth")

            return apiConfiguration
                .getConfiguration(CGK_API_ENDPOINT, authorization, connectivityInterceptor)
                .create(CoinGeckoApiService::class.java)
        }
    }

}