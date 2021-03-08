package com.openbank.data.mapper

import com.google.gson.Gson
import com.openbank.data.entity.MarvelCharactersResponse
import com.openbank.data.testutils.CharacterListMockData
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CharactersDetailsMapperImplTest {

    private lateinit var characterListDetailsMapperImpl: CharactersDetailsMapperImpl

    @BeforeEach
    fun setUp() {
        characterListDetailsMapperImpl = CharactersDetailsMapperImpl()
    }

    @Test
    fun getCharactersDetailUiModel() {

        val marvelCharactersResponse = Gson().fromJson(
            CharacterListMockData.getCharacterDetails(),
            MarvelCharactersResponse::class.java
        )

        val result =
            characterListDetailsMapperImpl.getCharactersDetailUiModel(marvelCharactersResponse)

        MatcherAssert.assertThat(result.size, Matchers.`is`(4))
        MatcherAssert.assertThat(result[0].title, Matchers.`is`("Comics"))

    }
}