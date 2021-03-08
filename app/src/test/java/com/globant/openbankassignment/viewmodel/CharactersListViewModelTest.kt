package com.globant.openbankassignment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.globant.openbankassignment.ui.characterslist.CharactersListViewModel
import com.openbank.domain.model.CharacterListModel
import com.openbank.domain.usecase.MarvelCharactersListUseCase
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

internal class CharactersListViewModelTest {

    @Mock
    lateinit var marvelCharactersListUseCase: MarvelCharactersListUseCase

    lateinit var charactersListViewModel: CharactersListViewModel

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()


    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        charactersListViewModel = CharactersListViewModel(marvelCharactersListUseCase)
    }

    @Test
    fun `getGetCharactersList`() {

        val mockedList: List<CharacterListModel>? = listOf(
            CharacterListModel(
                "3-D Man",
                "3-D Man is the name of two fictional superheroes appearing in American comic books published by Marvel Comics. ",
                "https://3dman.source.com",
                1011334L
            ),
            CharacterListModel(
                "Iron Man",
                "\n" +
                        "Iron Man is a 2008 American superhero film based on the Marvel Comics character of the same name.",
                "https://ironman.source.com",
                1011335L
            )
        )

        Mockito.`when`(marvelCharactersListUseCase.getCharactersList(0))
            .thenReturn(Observable.just(mockedList))

        charactersListViewModel.getCharactersList(0)

        Mockito.verify(marvelCharactersListUseCase).getCharactersList(0)


    }
}