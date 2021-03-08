package com.openbank.domain.usecase

import com.openbank.domain.model.CharacterListModel
import io.reactivex.Observable

interface MarvelCharactersListUseCase {

     fun getCharactersList(offSet:Int): Observable<List<CharacterListModel>>
}