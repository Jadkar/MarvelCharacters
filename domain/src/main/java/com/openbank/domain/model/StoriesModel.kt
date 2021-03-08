package com.openbank.domain.model

data class StoriesModel (
    var available: Long = 0,
    var collectionURI: String? = null,
    var storiesItems: List<StoriesItemModel>? = null,
    var returned: Long = 0
)
