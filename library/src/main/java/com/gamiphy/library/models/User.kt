package com.gamiphy.library.models

data class User(
    var email: String,
    var name: String,
    var hash: String = "",
    var referral: Referral? = null,
    var user: String="", // userId
    var avatar: String = ""
)