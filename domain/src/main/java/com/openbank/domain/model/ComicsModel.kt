package com.openbank.domain.model

data class ComicsModel (
    var available: Long = 0,
    var collectionURI: String? = null,
    var items: List<ItemModel>? = null,
    var returned: Long = 0
)