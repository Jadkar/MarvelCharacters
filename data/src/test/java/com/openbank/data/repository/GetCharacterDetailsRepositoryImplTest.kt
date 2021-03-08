package com.openbank.data.repository

import com.openbank.data.remote.GetCharacterDetailsRemoteSource
import com.openbank.domain.model.*
import com.openbank.domain.repository.GetCharactersDetailsRepository
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

internal class GetCharacterDetailsRepositoryImplTest {

    @Mock
    lateinit var getCharacterDetailRemote: GetCharacterDetailsRemoteSource
    lateinit var getCharactersDetailsRepository: GetCharactersDetailsRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getCharactersDetailsRepository = GetCharacterDetailsRepositoryImpl(getCharacterDetailRemote)
    }

    @Test
    fun getCharactersDetailsById() {

        Mockito.`when`(getCharacterDetailRemote.getMarvelCharacterDetails(1011334L))
            .thenReturn(Observable.just(getMockCharacterDetailsResponse()))

        getCharactersDetailsRepository.getCharactersDetailsById(1011334L)

        Mockito.verify(getCharacterDetailRemote).getMarvelCharacterDetails(1011334L)
        val result = getCharactersDetailsRepository.getCharactersDetailsById(1011334L)

        val testObserver = TestObserver<List<CharacterDetailsModel>>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        val listResult = testObserver.values()[0]
        MatcherAssert.assertThat(listResult.size, Matchers.`is`(3))
        MatcherAssert.assertThat(listResult[0].title, Matchers.`is`("Comics"))
        MatcherAssert.assertThat(listResult[1].title, Matchers.`is`("Series"))
    }

    private fun getMockCharacterDetailsResponse(): List<CharacterDetailsModel> {
        //Comics
        val comicsItem: List<ItemModel> = listOf(
            ItemModel(
                "http://gateway.marvel.com/v1/public/comics/24571",
                "Avengers: The Initiative (2007) #14 (SPOTLIGHT VARIANT)"
            ),
            ItemModel(
                "http://gateway.marvel.com/v1/public/comics/21546",
                "Avengers: The Initiative (2007) #15"
            )
        )
        val comicsModel = ComicsModel(
            12L,
            "http://gateway.marvel.com/v1/public/characters/1011334/comics",
            comicsItem,
            12
        )

        // Series
        val seriesItem: List<ItemModel> = listOf(
            ItemModel(
                "http://gateway.marvel.com/v1/public/series/1945",
                "Avengers: The Initiative (2007 - 2010)"
            ),
            ItemModel("http://gateway.marvel.com/v1/public/series/2005", "Deadpool (1997 - 2002)")
        )
        val seriesModel = SeriesModel(
            3,
            "http://gateway.marvel.com/v1/public/characters/1011334/series",
            seriesItem,
            3
        )


        // Series
        val storiesItem: List<StoriesItemModel> = listOf(
            StoriesItemModel(
                "http://gateway.marvel.com/v1/public/stories/19947",
                "Cover #19947",
                "cover"
            ),
            StoriesItemModel(
                "http://gateway.marvel.com/v1/public/stories/19948",
                "The 3-D Man!",
                "interiorStory"
            )
        )
        val storiesModel = StoriesModel(
            21,
            "http://gateway.marvel.com/v1/public/characters/1011334/stories",
            storiesItem,
            21
        )

        return listOf(
            CharacterDetailsModel("Comics", comicsModel),
            CharacterDetailsModel("Series", null, seriesModel),
            CharacterDetailsModel("Stories", null, null, storiesModel)
        )
    }
}