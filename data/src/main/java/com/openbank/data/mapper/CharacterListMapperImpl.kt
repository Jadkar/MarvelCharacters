package com.openbank.data.mapper

import com.openbank.data.entity.MarvelCharactersResponse
import com.openbank.domain.model.CharacterListModel

class CharacterListMapperImpl :
    CharacterListMapper {

    override fun getCharactersListUiModel(marvelCharactersResponse: MarvelCharactersResponse): List<CharacterListModel> {
        val characterListMapperArray = ArrayList<CharacterListModel>()

        if (marvelCharactersResponse.data?.results?.size ?: 0 > 0) {
            for (result in marvelCharactersResponse.data?.results ?: emptyList()) {
                val characterListMapper =
                    CharacterListModel()
                characterListMapper.characterName = result.name
                characterListMapper.characterId = result.id
                characterListMapper.characterDescription = result.description
                characterListMapper.characterUrl =
                    result.thumbnail?.path + "." + result.thumbnail?.extension

                characterListMapperArray.add(characterListMapper)
            }
        }

        return characterListMapperArray
    }
}