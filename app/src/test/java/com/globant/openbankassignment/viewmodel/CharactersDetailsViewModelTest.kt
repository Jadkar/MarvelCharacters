package com.globant.openbankassignment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.globant.openbankassignment.ui.charactersdetails.CharactersDetailsViewModel
import com.openbank.domain.model.*
import com.openbank.domain.usecase.MarvelCharactersDetailsUseCase
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

internal class CharactersDetailsViewModelTest {

    @Mock
    lateinit var marvelCharactersDetailsUseCase: MarvelCharactersDetailsUseCase

    lateinit var charactersDetailsViewModel: CharactersDetailsViewModel

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        charactersDetailsViewModel = CharactersDetailsViewModel(marvelCharactersDetailsUseCase)
    }

    @Test
    fun getCharactersDetails() {
        Mockito.`when`(marvelCharactersDetailsUseCase.getCharactersDetailsList(1011334L))
            .thenReturn(Observable.just(getMockCharacterDetailsResponse()))

        charactersDetailsViewModel.getCharactersDetails(1011334L)

        Mockito.verify(marvelCharactersDetailsUseCase).getCharactersDetailsList(1011334L)
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