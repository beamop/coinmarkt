![coinmarkt logo](img/logo.png)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=beamop_coinmarkt&metric=alert_status)](https://sonarcloud.io/dashboard?id=beamop_coinmarkt)

`coinmarkt` is an Android app to get accurate cryptocurrencies and exchanges data from CoinGecko, you also get news from various sources.

Feel free to contribute, I'm still learning Android development and Kotlin.

## Getting started

You can use Android Studio or IntelliJ IDEA to work with this repository.

Or you can clone the repository manually :
`git clone https://github.com/beamop/coinmarkt.git`

<img src="/img/demo.gif" align="right" width="32%"/>

## Features

- Cryptocurrencies listing
	- Change (24h)
	- Sparkline
	- Price (USD)
- Cryptocurrencies details
	- Line chart
	- Price (USD)
	- Rank
	- Volume
	- Variation (24h)
	- Low / Hight (24)
- Exchanges listing
	- Trust score
	- Change (24h)
	- Redirect to website on click
- Latest news
	- Aggregated top cryptocurrency articles
	- Redirect to article on click

## Architecture
Coinmarkt is based on MVVM architecture and a repository pattern.

![architecture](https://user.oc-static.com/upload/2018/03/13/15209311930352_final-architecture.png)

## Acknowledgements

CoinGecko.com for providing free access to the API :rocket:
CryptoControl.io for providing high quality news API :newspaper:

And all the dependencies used in this project, you can get the full list using :
`gradle app:dependencies`
