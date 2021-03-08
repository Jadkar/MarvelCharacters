package com.globant.openbankassignment.ui.charactersdetails

import androidx.lifecycle.MutableLiveData
import com.globant.openbankassignment.ui.base.BaseViewModel
import com.openbank.domain.model.CharacterDetailsModel
import com.openbank.domain.usecase.MarvelCharactersDetailsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharactersDetailsViewModel @Inject constructor(private val useCaseImpl: MarvelCharactersDetailsUseCase) :
    BaseViewModel() {

    private lateinit var disposableCharacterDetails: DisposableObserver<List<CharacterDetailsModel>>

    var characterDetails: MutableLiveData<List<CharacterDetailsModel>> = MutableLiveData()
    var getCharacterDetailsFailure: MutableLiveData<String> = MutableLiveData()

    fun getCharactersDetails(characterId: Long) {

        disposableCharacterDetails = object : DisposableObserver<List<CharacterDetailsModel>>() {
            override fun onComplete() {
            }

            override fun onNext(t: List<CharacterDetailsModel>) {
                characterDetails.postValue(t)
            }

            override fun onError(e: Throwable) {
                getCharacterDetailsFailure.postValue(e.message)
            }
        }
        useCaseImpl.getCharactersDetailsList(characterId)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(disposableCharacterDetails)
    }

}