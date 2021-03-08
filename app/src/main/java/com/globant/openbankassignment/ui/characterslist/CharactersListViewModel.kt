package com.globant.openbankassignment.ui.characterslist

import androidx.lifecycle.MutableLiveData
import com.openbank.domain.usecase.MarvelCharactersListUseCase
import com.globant.openbankassignment.ui.base.BaseViewModel
import com.openbank.domain.model.CharacterListModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharactersListViewModel @Inject constructor(private val useCaseCharactersList: MarvelCharactersListUseCase) :
    BaseViewModel() {

    private lateinit var disposableObserverCharacters: DisposableObserver<List<CharacterListModel>>
    internal var getCharactersFailure: MutableLiveData<String> = MutableLiveData()

    internal var charactersResponse: MutableLiveData<List<CharacterListModel>> = MutableLiveData()

    fun getCharactersList(offSet: Int) {

        disposableObserverCharacters = object : DisposableObserver<List<CharacterListModel>>() {
            override fun onComplete() {
            }
            override fun onError(e: Throwable) {
                getCharactersFailure.postValue(e.message)
            }
            override fun onNext(t: List<CharacterListModel>) {
                charactersResponse.postValue(t)

            }
        }
        useCaseCharactersList.getCharactersList(offSet)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(disposableObserverCharacters)

    }
}