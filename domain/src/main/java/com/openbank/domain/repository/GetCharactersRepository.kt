package com.openbank.domain.repository

import com.openbank.domain.model.CharacterListModel
import io.reactivex.Observable


interface GetCharactersRepository {
    fun getCharacters(offset:Int): Observable<List<CharacterListModel>>
}