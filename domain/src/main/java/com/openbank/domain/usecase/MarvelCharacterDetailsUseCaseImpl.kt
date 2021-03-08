package com.openbank.domain.usecase


import com.openbank.domain.repository.GetCharactersDetailsRepository
import com.openbank.domain.model.CharacterDetailsModel
import io.reactivex.Observable
import javax.inject.Inject

class MarvelCharacterDetailsUseCaseImpl @Inject constructor(
    val repository: GetCharactersDetailsRepository
) : MarvelCharactersDetailsUseCase {

    override fun getCharactersDetailsList(characterId: Long): Observable<List<CharacterDetailsModel>> {
        return repository.getCharactersDetailsById(characterId)
    }
}