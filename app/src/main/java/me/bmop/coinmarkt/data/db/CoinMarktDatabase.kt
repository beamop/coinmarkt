package me.bmop.coinmarkt.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.bmop.coinmarkt.data.db.converters.Converters
import me.bmop.coinmarkt.data.db.entity.cgk.cryptocurrencies.CoinGeckoCryptocurrenciesEntry
import me.bmop.coinmarkt.data.db.entity.cgk.exchanges.CoinGeckoExchangesEntry

@Database(
    entities = [CoinGeckoCryptocurrenciesEntry::class, CoinGeckoExchangesEntry::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class CoinMarktDatabase : RoomDatabase() {
    abstract fun coinGeckoCryptocurrenciesDao(): CoinMarktCryptocurrenciesDao
    abstract fun coinGeckoExchangesDao(): CoinMarktExchangesDao

    companion object {
        @Volatile private var instance: CoinMarktDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            context.deleteDatabase("coinmarkt.db")
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                CoinMarktDatabase::class.java, "coinmarkt.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}