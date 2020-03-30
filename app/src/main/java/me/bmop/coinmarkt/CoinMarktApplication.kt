package me.bmop.coinmarkt

import android.app.Application
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import me.bmop.coinmarkt.data.db.CoinMarktDatabase
import me.bmop.coinmarkt.data.network.CoinGeckoDataSource
import me.bmop.coinmarkt.data.network.CoinGeckoDataSourceImpl
import me.bmop.coinmarkt.data.network.ConnectivityInterceptor
import me.bmop.coinmarkt.data.network.ConnectivityInterceptorImpl
import me.bmop.coinmarkt.data.repository.CoinGeckoRepository
import me.bmop.coinmarkt.data.repository.CoinGeckoRepositoryImpl
import me.bmop.coinmarkt.service.ApiConfiguration
import me.bmop.coinmarkt.service.CoinGeckoApiService
import me.bmop.coinmarkt.service.CryptoControlApiService
import me.bmop.coinmarkt.ui.cryptocurrencies.CryptocurrenciesViewModelFactory
import me.bmop.coinmarkt.ui.exchanges.ExchangesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class CoinMarktApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@CoinMarktApplication))

        bind() from singleton { CoinMarktDatabase(instance()) }
        bind() from singleton { instance<CoinMarktDatabase>().coinGeckoCryptocurrenciesDao() }
        bind() from singleton { instance<CoinMarktDatabase>().coinGeckoExchangesDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { CoinGeckoApiService(ApiConfiguration(), instance()) }
        bind() from singleton { CryptoControlApiService(ApiConfiguration(), instance()) }
        bind<CoinGeckoDataSource>() with singleton { CoinGeckoDataSourceImpl(instance()) }
        bind<CoinGeckoRepository>() with singleton {
            CoinGeckoRepositoryImpl(
                instance(),
                instance(),
                instance()
            )
        }
        bind() from provider { CryptocurrenciesViewModelFactory(instance()) }
        bind() from provider { ExchangesViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        Stetho.initializeWithDefaults(this);
    }
}