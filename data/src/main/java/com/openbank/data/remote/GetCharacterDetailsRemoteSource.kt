package com.openbank.data.remote

import com.openbank.domain.model.CharacterDetailsModel
import io.reactivex.Observable

interface GetCharacterDetailsRemoteSource {

 fun getMarvelCharacterDetails(characterId:Long): Observable<List<CharacterDetailsModel>>
}