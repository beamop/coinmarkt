package me.bmop.coinmarkt.data.network.response

import me.bmop.coinmarkt.data.db.entity.cmc.cryptocurrencies.CoinMarketCapCryptocurrenciesEntry
import me.bmop.coinmarkt.data.db.entity.cmc.cryptocurrencies.Status

data class CoinMarketCapCryptocurrenciesResponse(
    val status: Status,
    val data: List<CoinMarketCapCryptocurrenciesEntry>
)