package com.openbank.data.mapper

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.openbank.data.entity.MarvelCharactersResponse
import com.openbank.domain.model.*
import java.lang.reflect.Type

class CharactersDetailsMapperImpl :
    CharactersDetailsMapper {

    override fun getCharactersDetailUiModel(marvelCharactersResponse: MarvelCharactersResponse): List<CharacterDetailsModel> {
        val characterDetailsMapper = ArrayList<CharacterDetailsModel>()

        // Comics
        marvelCharactersResponse.data?.results?.get(0)?.comics?.items?.let {
            if (marvelCharactersResponse.data?.results?.get(0)?.comics?.items?.size ?: 0 > 0) {
                val characterComicsTitle: String? = CharactersDeatilsType.COMICS.value

                val jsonInString: String =
                    Gson().toJson(marvelCharactersResponse.data?.results?.get(0)?.comics)
                val itemComics: ComicsModel = Gson().fromJson(jsonInString, ComicsModel::class.java)

                val characterDetailsComics =
                    CharacterDetailsModel()
                characterDetailsComics.title = characterComicsTitle
                characterDetailsComics.comics = itemComics
                characterDetailsMapper.add(characterDetailsComics)
            }
        }
        //Series
        marvelCharactersResponse.data?.results?.get(0)?.series?.items.let {
            if (marvelCharactersResponse.data?.results?.get(0)?.series?.items?.size ?: 0 > 0) {
                val characterSeriesTitle: String? = CharactersDeatilsType.SERIES.value

                val jsonInItemSeries: String =
                    Gson().toJson(marvelCharactersResponse.data?.results?.get(0)?.series)

                val itemSeries: SeriesModel =
                    Gson().fromJson(jsonInItemSeries, SeriesModel::class.java)

                val characterDetailsSeries =
                    CharacterDetailsModel()
                characterDetailsSeries.title = characterSeriesTitle
                characterDetailsSeries.series = itemSeries

                characterDetailsMapper.add(characterDetailsSeries)
            }
        }


        //Stories
        marvelCharactersResponse.data?.results?.get(0)?.stories?.storiesItems.let {
            if (marvelCharactersResponse.data?.results?.get(0)?.stories?.storiesItems?.size ?: 0 > 0) {
                val characterStoriesTitle: String? = CharactersDeatilsType.STORIES.value

                val jsonInItemStories: String =
                    Gson().toJson(marvelCharactersResponse.data?.results?.get(0)?.stories)

                val itemStories: StoriesModel =
                    Gson().fromJson(jsonInItemStories, StoriesModel::class.java)

                val characterDetailsStories =
                    CharacterDetailsModel()
                characterDetailsStories.title = characterStoriesTitle
                characterDetailsStories.stories = itemStories

                characterDetailsMapper.add(characterDetailsStories)
            }
        }

        //Events
        marvelCharactersResponse.data?.results?.get(0)?.stories?.storiesItems.let {
            if (marvelCharactersResponse.data?.results?.get(0)?.stories?.storiesItems?.size ?: 0 > 0) {
                val characterEventsTitle: String? = CharactersDeatilsType.EVENTS.value

                val jsonInItemEvents: String =
                    Gson().toJson(marvelCharactersResponse.data?.results?.get(0)?.events)

                val itemEvents: EventsModel =
                    Gson().fromJson(jsonInItemEvents, EventsModel::class.java)

                val characterDetailsEvents =
                    CharacterDetailsModel()
                characterDetailsEvents.title = characterEventsTitle
                characterDetailsEvents.events = itemEvents

                characterDetailsMapper.add(characterDetailsEvents)
            }
        }

        //Details Link URLS
        marvelCharactersResponse.data?.results?.get(0)?.urls?.let {
            if (marvelCharactersResponse.data?.results?.get(0)?.urls?.size ?: 0 > 0) {
                val characterUrlsTitle: String? =
                    CharactersDeatilsType.CHARACTERSDETAILSSOURCE.value

                val jsonInitemUrl =
                    Gson().toJsonTree(marvelCharactersResponse.data?.results?.get(0)?.urls!!)

                val type: Type = object : TypeToken<List<UrlModel?>?>() {}.type

                val itemUrl: List<UrlModel> = Gson().fromJson(jsonInitemUrl, type)

                val characterDetailsUrls =
                    CharacterDetailsModel()
                characterDetailsUrls.title = characterUrlsTitle
                characterDetailsUrls.urls = itemUrl

                characterDetailsMapper.add(characterDetailsUrls)
            }
        }


        return characterDetailsMapper
    }
}