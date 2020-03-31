package me.bmop.coinmarkt

import android.app.Application
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import me.bmop.coinmarkt.data.db.CoinMarktDatabase
import me.bmop.coinmarkt.data.network.datasource.cgk.CoinGeckoDataSource
import me.bmop.coinmarkt.data.network.datasource.cgk.CoinGeckoDataSourceImpl
import me.bmop.coinmarkt.data.network.ConnectivityInterceptor
import me.bmop.coinmarkt.data.network.ConnectivityInterceptorImpl
import me.bmop.coinmarkt.data.network.datasource.cc.CryptoControlDataSource
import me.bmop.coinmarkt.data.network.datasource.cc.CryptoControlDataSourceImpl
import me.bmop.coinmarkt.data.repository.cc.CryptoControlRepository
import me.bmop.coinmarkt.data.repository.cc.CryptoControlRepositoryImpl
import me.bmop.coinmarkt.data.repository.cgk.CoinGeckoRepository
import me.bmop.coinmarkt.data.repository.cgk.CoinGeckoRepositoryImpl
import me.bmop.coinmarkt.service.ApiConfiguration
import me.bmop.coinmarkt.service.CoinGeckoApiService
import me.bmop.coinmarkt.service.CryptoControlApiService
import me.bmop.coinmarkt.ui.viewmodel.cc.news.NewsViewModelFactory
import me.bmop.coinmarkt.ui.viewmodel.cgk.cryptocurrencies.CryptocurrenciesViewModelFactory
import me.bmop.coinmarkt.ui.viewmodel.cgk.exchanges.ExchangesViewModelFactory
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
        bind() from singleton { instance<CoinMarktDatabase>().cryptoControlNewsDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { CoinGeckoApiService(ApiConfiguration(), instance()) }
        bind() from singleton { CryptoControlApiService(ApiConfiguration(), instance()) }
        bind<CoinGeckoDataSource>() with singleton {
            CoinGeckoDataSourceImpl(
                instance()
            )
        }
        bind<CryptoControlDataSource>() with singleton {
            CryptoControlDataSourceImpl(
                instance()
            )
        }
        bind<CoinGeckoRepository>() with singleton {
            CoinGeckoRepositoryImpl(
                instance(),
                instance(),
                instance()
            )
        }
        bind<CryptoControlRepository>() with singleton {
            CryptoControlRepositoryImpl(
                instance(),
                instance()
            )
        }
        bind() from provider {
            CryptocurrenciesViewModelFactory(
                instance()
            )
        }
        bind() from provider {
            ExchangesViewModelFactory(
                instance()
            )
        }
        bind() from provider {
            NewsViewModelFactory(
                instance()
            )
        }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        Stetho.initializeWithDefaults(this);
    }
}