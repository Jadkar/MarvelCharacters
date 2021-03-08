package com.openbank.data.entity


data class Result (

    var id: Long = 0,
    var name: String? = null,
    var description: String? = null,
    var modified: String? = null,
    var thumbnail: Thumbnail? = null,
    var resourceURI: String? = null,
    var comics: Comics? = null,
    var series: Series? = null,
    var stories: Stories? = null,
    var events: Events? = null,
    var urls: List<Url>? = null

)