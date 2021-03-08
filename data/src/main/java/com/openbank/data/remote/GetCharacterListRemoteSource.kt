package com.openbank.data.remote

import com.openbank.domain.model.CharacterListModel
import io.reactivex.Observable

interface GetCharacterListRemoteSource {

 fun getMarvelCharacterList(offset:Int): Observable<List<CharacterListModel>>
}