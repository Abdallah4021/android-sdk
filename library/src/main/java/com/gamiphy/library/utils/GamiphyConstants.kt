package com.gamiphy.library.utils

object GamiphyConstants {

    const val API_V1 = "api/v1/"

    val BOT_API = if (GamiphyData.getInstance().debug) {
        ".bot.dev.gamiphy.co/"
    } else {
        ".bot.gamiphy.co/"
    }

    val GAMIPHY_API_DOMAIN = if (GamiphyData.getInstance().debug) {
        "https://api.dev.gamiphy.co/"
    } else {
        "https://api.gamiphy.co/"
    }

}
