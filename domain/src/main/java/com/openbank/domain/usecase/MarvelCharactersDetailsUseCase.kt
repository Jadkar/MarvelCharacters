package com.openbank.domain.usecase

import com.openbank.domain.model.CharacterDetailsModel
import io.reactivex.Observable

interface MarvelCharactersDetailsUseCase {
     fun getCharactersDetailsList(characterId:Long):  Observable<List<CharacterDetailsModel>>
}