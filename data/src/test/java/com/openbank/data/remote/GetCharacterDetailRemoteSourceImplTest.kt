package com.openbank.data.remote

import com.google.gson.Gson
import com.openbank.data.entity.MarvelCharactersResponse
import com.openbank.data.mapper.CharactersDetailsMapper
import com.openbank.data.testutils.RxImmediateSchedulerRule
import com.openbank.domain.model.*
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit

internal class GetCharacterDetailRemoteSourceImplTest {

    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var marvelApi: MarvelApi

    @Mock
    lateinit var charactersDetailsMapper: CharactersDetailsMapper

    lateinit var getCharacterDetailsRemoteSource: GetCharacterDetailsRemoteSource

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getCharacterDetailsRemoteSource =
            GetCharacterDetailRemoteSourceImpl(marvelApi, charactersDetailsMapper)

    }

    @Test
    fun getMarvelCharacterDetails() {

        val mockJsonData =
            "{\"code\":200,\"status\":\"Ok\",\"copyright\":\"© 2021 MARVEL\",\"attributionText\":\"Data provided by Marvel. © 2021 MARVEL\",\"attributionHTML\":\"<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2021 MARVEL</a>\",\"etag\":\"a690b7fd9a78b91686070d8c0244e0d8a56a3532\",\"data\":{\"offset\":0,\"limit\":20,\"total\":1493,\"count\":20,\"results\":[{\"id\":1017100,\"name\":\"A-Bomb (HAS)\",\"description\":\"Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction! \",\"modified\":\"2013-09-18T15:54:04-0400\",\"thumbnail\":{\"path\":\"http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16\",\"extension\":\"jpg\"},\"resourceURI\":\"http://gateway.marvel.com/v1/public/characters/1017100\",\"comics\":{\"available\":3,\"collectionURI\":\"http://gateway.marvel.com/v1/public/characters/1017100/comics\",\"items\":[{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/40632\",\"name\":\"Hulk (2008) #53\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/40630\",\"name\":\"Hulk (2008) #54\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/40628\",\"name\":\"Hulk (2008) #55\"}],\"returned\":3},\"series\":{\"available\":2,\"collectionURI\":\"http://gateway.marvel.com/v1/public/characters/1017100/series\",\"items\":[{\"resourceURI\":\"http://gateway.marvel.com/v1/public/series/17765\",\"name\":\"FREE COMIC BOOK DAY 2013 1 (2013)\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/series/3374\",\"name\":\"Hulk (2008 - 2012)\"}],\"returned\":2},\"stories\":{\"available\":7,\"collectionURI\":\"http://gateway.marvel.com/v1/public/characters/1017100/stories\",\"items\":[{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/92078\",\"name\":\"Hulk (2008) #55\",\"type\":\"cover\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/92079\",\"name\":\"Interior #92079\",\"type\":\"interiorStory\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/92082\",\"name\":\"Hulk (2008) #54\",\"type\":\"cover\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/92083\",\"name\":\"Interior #92083\",\"type\":\"interiorStory\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/92086\",\"name\":\"Hulk (2008) #53\",\"type\":\"cover\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/92087\",\"name\":\"Interior #92087\",\"type\":\"interiorStory\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/105929\",\"name\":\"cover from Free Comic Book Day 2013 (Avengers/Hulk) (2013) #1\",\"type\":\"cover\"}],\"returned\":7},\"events\":{\"available\":0,\"collectionURI\":\"http://gateway.marvel.com/v1/public/characters/1017100/events\",\"items\":[],\"returned\":0},\"urls\":[{\"type\":\"detail\",\"url\":\"http://marvel.com/characters/76/a-bomb?utm_campaign=apiRef&utm_source=aaf8c52b225738170b254090a7f12cf3\"},{\"type\":\"comiclink\",\"url\":\"http://marvel.com/comics/characters/1017100/a-bomb_has?utm_campaign=apiRef&utm_source=aaf8c52b225738170b254090a7f12cf3\"}]}]}}"
        val marvelCharactersResponse =
            Gson().fromJson(mockJsonData, MarvelCharactersResponse::class.java)
        Mockito.`when`(marvelApi.getCharactersById(1017100L))
            .thenReturn(Observable.just(marvelCharactersResponse))
        Mockito.`when`(charactersDetailsMapper.getCharactersDetailUiModel(marvelCharactersResponse))
            .thenReturn(getMockCharacterDetailsResponse())

        val result = getCharacterDetailsRemoteSource.getMarvelCharacterDetails(1017100L)

        val testObserver = TestObserver<List<CharacterDetailsModel>>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        val listResult = testObserver.values()[0]
        MatcherAssert.assertThat(listResult.size, Matchers.`is`(3))
        MatcherAssert.assertThat(
            listResult[0].comics?.collectionURI,
            Matchers.`is`("http://gateway.marvel.com/v1/public/characters/1017100/comics")
        )
        MatcherAssert.assertThat(listResult[0].comics?.returned, Matchers.`is`(3L))
        MatcherAssert.assertThat(listResult[2].stories?.storiesItems?.size, Matchers.`is`(2))
        MatcherAssert.assertThat(
            listResult[2].stories?.storiesItems?.get(0)?.name,
            Matchers.`is`("Hulk (2008) #55")
        )

    }

    private fun getMockCharacterDetailsResponse(): List<CharacterDetailsModel> {
        //Comics
        val comicsItem: List<ItemModel> = listOf(
            ItemModel("http://gateway.marvel.com/v1/public/comics/40632", "Hulk (2008) #53"),
            ItemModel("http://gateway.marvel.com/v1/public/comics/40630", "Hulk (2008) #54")
        )
        val comicsModel = ComicsModel(
            2,
            "http://gateway.marvel.com/v1/public/characters/1017100/comics",
            comicsItem,
            3L
        )

        // Series
        val seriesItem: List<ItemModel> = listOf(
            ItemModel(
                "http://gateway.marvel.com/v1/public/series/17765",
                "FREE COMIC BOOK DAY 2013 1 (2013)"
            ),
            ItemModel("http://gateway.marvel.com/v1/public/series/3374", "Hulk (2008 - 2012)")
        )
        val seriesModel = SeriesModel(
            2,
            "http://gateway.marvel.com/v1/public/characters/1017100/series",
            seriesItem,
            2
        )


        // Series
        val storiesItem: List<StoriesItemModel> = listOf(
            StoriesItemModel(
                "http://gateway.marvel.com/v1/public/stories/92078",
                "Hulk (2008) #55",
                "cover"
            ),
            StoriesItemModel(
                "http://gateway.marvel.com/v1/public/stories/92079",
                "Interior #92079",
                "interiorStory"
            )
        )
        val storiesModel = StoriesModel(
            7,
            "http://gateway.marvel.com/v1/public/characters/1017100/stories",
            storiesItem,
            7
        )

        val mockedList: List<CharacterDetailsModel> = listOf(
            CharacterDetailsModel("Comics", comicsModel),
            CharacterDetailsModel("Series", null, seriesModel),
            CharacterDetailsModel("Stories", null, null, storiesModel)
        )

        return mockedList
    }

}