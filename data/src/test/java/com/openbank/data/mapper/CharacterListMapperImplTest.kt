package com.openbank.data.mapper

import com.google.gson.Gson
import com.openbank.data.entity.MarvelCharactersResponse
import com.openbank.data.testutils.CharacterListMockData
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CharacterListMapperImplTest {

    private lateinit var characterListMapperImpl: CharacterListMapperImpl

    @BeforeEach
    fun setUp() {
        characterListMapperImpl = CharacterListMapperImpl()
    }

    @Test
    fun getCharactersListUiModel() {

        val marvelCharactersResponse = Gson().fromJson(
            CharacterListMockData.getCharacterList(),
            MarvelCharactersResponse::class.java
        )

        val result = characterListMapperImpl.getCharactersListUiModel(marvelCharactersResponse)

        assertThat(result.size, `is`(3))
        assertThat(result[0].characterId, `is`(1011334L))
        assertThat(result[0].characterName, `is`("3-D Man"))

    }
}