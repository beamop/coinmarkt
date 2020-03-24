package me.bmop.coinmarkt.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.bmop.coinmarkt.data.db.entity.cmc.cryptocurrencies.CoinMarketCapCryptocurrenciesEntry
import me.bmop.coinmarkt.data.db.entity.cmc.exchanges.CoinMarketCapExchangesEntry

@Database(
    entities = [CoinMarketCapCryptocurrenciesEntry::class, CoinMarketCapExchangesEntry::class],
    version = 5,
    exportSchema = true
)
abstract class CoinMarktDatabase : RoomDatabase() {
    abstract fun coinMarketCapCryptocurrenciesDao(): CoinMarktCryptocurrenciesDao
    abstract fun coinMarketCapExchangesDao(): CoinMarktExchangesDao

    companion object {
        @Volatile private var instance: CoinMarktDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                CoinMarktDatabase::class.java, "coinmarkt.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}