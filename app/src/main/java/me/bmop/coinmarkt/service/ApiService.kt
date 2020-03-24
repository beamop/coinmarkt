package me.bmop.coinmarkt.service

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import me.bmop.coinmarkt.data.network.ConnectivityInterceptor
import me.bmop.coinmarkt.data.network.response.CoinMarketCapCryptocurrenciesResponse
import me.bmop.coinmarkt.data.network.response.CoinMarketCapExchangesResponse
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

const val CMC_PROD_API_ENDPOINT = "EDIT_ME"
const val CMC_PROD_API_KEY = "EDIT_ME"
const val CMC_SANDBOX_API_ENDPOINT = "EDIT_ME"
const val CMC_SANDBOX_API_KEY = "EDIT_ME"

interface ApiService {

    @GET("cryptocurrency/listings/latest")
    fun getCoinMarketCapListings(
        @Query("start") start: Int,
        @Query("limit") limit: Int,
        @Query("convert") currency: String
    ): Deferred<CoinMarketCapCryptocurrenciesResponse>

    @GET("exchange/listings/latest")
    fun getCoinMarketCapExchanges(): Deferred<CoinMarketCapExchangesResponse>

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): ApiService {
            val requestInterceptor = Interceptor { chain ->
                val url: HttpUrl = chain.request()
                    .url
                    .newBuilder()
                    .addQueryParameter("CMC_PRO_API_KEY", CMC_SANDBOX_API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(CMC_SANDBOX_API_ENDPOINT)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}