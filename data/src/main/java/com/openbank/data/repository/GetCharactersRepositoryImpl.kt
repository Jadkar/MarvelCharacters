package com.openbank.data.repository

import com.openbank.data.remote.GetCharacterListRemoteSource
import com.openbank.domain.repository.GetCharactersRepository
import com.openbank.domain.model.CharacterListModel
import io.reactivex.Observable
import javax.inject.Inject

class GetCharactersRepositoryImpl @Inject constructor(private val getCharacterListRemoteSource: GetCharacterListRemoteSource) :
    GetCharactersRepository {
    override fun getCharacters(offset: Int): Observable<List<CharacterListModel>> {
        return getCharacterListRemoteSource.getMarvelCharacterList(offset)
    }
}