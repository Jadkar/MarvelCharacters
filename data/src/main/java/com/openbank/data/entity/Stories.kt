package com.openbank.data.entity

data class Stories (
    var available: Long = 0,
    var collectionURI: String? = null,
    var storiesItems: List<StoriesItem>? = null,
    var returned: Long = 0
)