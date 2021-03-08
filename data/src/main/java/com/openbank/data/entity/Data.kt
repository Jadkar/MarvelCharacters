package com.openbank.data.entity


data class Data (
    var offset: Long = 0,
    var limit: Long = 0,
    var total: Long = 0,
    var count: Long = 0,
    var results: List<Result>? = null

)