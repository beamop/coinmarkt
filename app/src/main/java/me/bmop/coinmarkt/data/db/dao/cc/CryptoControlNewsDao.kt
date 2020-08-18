package me.bmop.coinmarkt.data.db.dao.cc

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.bmop.coinmarkt.data.db.entity.cc.news.CryptoControlNewsEntry

@Dao
interface CryptoControlNewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertCryptoControlNews(news: List<CryptoControlNewsEntry>)

    @Query(value = "select * from coinmarkt_cc_news")
    fun getCryptoControlNews(): LiveData<List<CryptoControlNewsEntry>>
}