package me.bmop.coinmarkt.data.network.response

import me.bmop.coinmarkt.data.db.entity.cmc.exchanges.CoinMarketCapExchangesEntry
import me.bmop.coinmarkt.data.db.entity.cmc.exchanges.Status

data class CoinMarketCapExchangesResponse(
    val status: Status,
    val data: List<CoinMarketCapExchangesEntry>
)