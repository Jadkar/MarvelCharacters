package com.openbank.data.entity

data class Series (

    var available: Long = 0,
    var collectionURI: String? = null,
    var items: List<Item>? = null,
    var returned: Long = 0
)
