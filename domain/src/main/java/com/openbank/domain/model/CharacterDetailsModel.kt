package com.openbank.domain.model

data class CharacterDetailsModel (
    var title: String? = null,
    var comics: ComicsModel? = null,
    var series: SeriesModel? = null,
    var stories: StoriesModel? = null,
    var events: EventsModel? = null,
    var urls: List<UrlModel>? = null
)
