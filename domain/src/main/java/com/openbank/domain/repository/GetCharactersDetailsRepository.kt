package com.openbank.domain.repository
import com.openbank.domain.model.CharacterDetailsModel
import io.reactivex.Observable

interface GetCharactersDetailsRepository {
    fun getCharactersDetailsById(characterId:Long):  Observable<List<CharacterDetailsModel>>
}