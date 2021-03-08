package com.openbank.data.mapper

import com.openbank.data.entity.MarvelCharactersResponse
import com.openbank.domain.model.CharacterListModel

interface CharacterListMapper {
    fun getCharactersListUiModel(marvelCharactersResponse: MarvelCharactersResponse):List<CharacterListModel>
}