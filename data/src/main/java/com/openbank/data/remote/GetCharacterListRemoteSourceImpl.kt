package com.openbank.data.remote

import com.openbank.data.mapper.CharacterListMapper
import com.openbank.domain.model.CharacterListModel
import io.reactivex.Observable
import javax.inject.Inject

class GetCharacterListRemoteSourceImpl @Inject constructor(
    private val service: MarvelApi,
    private val characterListMapper: CharacterListMapper
) : GetCharacterListRemoteSource {
    override fun getMarvelCharacterList(offset: Int): Observable<List<CharacterListModel>> {
        return  service.getCharactersList(offset).map {
             characterListMapper.getCharactersListUiModel(it)
         }
    }
}