package me.bmop.coinmarkt.service

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import me.bmop.coinmarkt.data.network.ConnectivityInterceptor
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val CMC_API_ENDPOINT  = "EDIT_ME"
const val CMC_API_KEY       = "EDIT_ME"
const val CC_API_ENDPOINT   = "EDIT_ME"
const val CC_API_KEY        = "EDIT_ME"

class ApiConfiguration {

    fun getConfiguration(endpoint: String,
                         authorization: Map<String, String>,
                         connectivityInterceptor: ConnectivityInterceptor): Retrofit {
        val requestInterceptor = Interceptor { chain ->
            val url: HttpUrl = chain.request()
                .url
                .newBuilder()
                .build()
            val request = chain.request()
                .newBuilder()
                .addHeader(authorization.getValue("headerKey"), authorization.getValue("headerValue"))
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
            .baseUrl(endpoint)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}