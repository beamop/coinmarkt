package me.bmop.coinmarkt.data.db.entity.cc.news

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coinmarkt_cc_news")
data class CryptoControlNewsEntry(
    val activityHotness: Double,
    val description: String,
    val hotness: Double,
    val id: String,
    val originalImageUrl: String,
    val primaryCategory: String,
    val publishedAt: String,
    @Embedded(prefix = "source_")
    val source: Source,
    val sourceDomain: String,
    val thumbnail: String? = null,
    val title: String,
    val url: String,
    val words: Int
) {
    @PrimaryKey(autoGenerate = true)
    var newsId: Int = 0
}