package com.openbank.domain.usecase

import com.openbank.domain.model.CharacterListModel
import com.openbank.domain.repository.GetCharactersRepository
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

internal class MarvelCharactersListUseCaseImplTest {

    @Mock
    lateinit var getCharactersRepository: GetCharactersRepository

    lateinit var marvelCharactersListUseCase: MarvelCharactersListUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        marvelCharactersListUseCase = MarvelCharactersListUseCaseImpl(getCharactersRepository)
    }

    @Test
    fun getCharactersList() {
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

        Mockito.`when`(getCharactersRepository.getCharacters(0))
            .thenReturn(Observable.just(mockedList))

        marvelCharactersListUseCase.getCharactersList(0)

        Mockito.verify(getCharactersRepository).getCharacters(0)
        val result = getCharactersRepository.getCharacters(0)

        val testObserver = TestObserver<List<CharacterListModel>>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        val listResult = testObserver.values()[0]
        MatcherAssert.assertThat(listResult.size, Matchers.`is`(2))
        MatcherAssert.assertThat(listResult[1].characterId, Matchers.`is`(1011335L))
        MatcherAssert.assertThat(listResult[1].characterName, Matchers.`is`("Iron Man"))

    }
}