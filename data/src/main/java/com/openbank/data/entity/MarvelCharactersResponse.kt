package com.openbank.data.entity


data class MarvelCharactersResponse (

    var code: Long = 0,
    var status: String? = null,
    var copyright: String? = null,
    var attributionText: String? = null,
    var attributionHTML: String? = null,
    var etag: String? = null,
    var data: Data? = null

)