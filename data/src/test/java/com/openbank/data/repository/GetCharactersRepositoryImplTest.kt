package com.openbank.data.repository

import com.openbank.data.remote.GetCharacterListRemoteSource
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

internal class GetCharactersRepositoryImplTest {

    @Mock
    lateinit var getCharacterListRemoteSource: GetCharacterListRemoteSource

    private lateinit var getCharactersRepository: GetCharactersRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getCharactersRepository = GetCharactersRepositoryImpl(getCharacterListRemoteSource)
    }

    @Test
    fun testGetCharactersSuccessful() {

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

        Mockito.`when`(getCharacterListRemoteSource.getMarvelCharacterList(0))
            .thenReturn(Observable.just(mockedList))

        getCharactersRepository.getCharacters(0)

        Mockito.verify(getCharacterListRemoteSource).getMarvelCharacterList(0)
        val result = getCharactersRepository.getCharacters(0)

        val testObserver = TestObserver<List<CharacterListModel>>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        val listResult = testObserver.values()[0]
        MatcherAssert.assertThat(listResult.size, Matchers.`is`(2))
        MatcherAssert.assertThat(listResult[0].characterId, Matchers.`is`(1011334L))
        MatcherAssert.assertThat(listResult[0].characterName, Matchers.`is`("3-D Man"))
    }
}