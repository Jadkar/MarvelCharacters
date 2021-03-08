package com.globant.openbankassignment.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.globant.openbankassignment.ui.charactersdetails.CharactersDetailsViewModel
import com.globant.openbankassignment.ui.characterslist.CharactersListViewModel

import com.globant.openbankassignment.utils.AppViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {
    @Binds
    @IntoMap
    @com.globant.openbankassignment.di.module.ViewModelKey(CharactersListViewModel::class)
    internal abstract fun bindCharactersListViewModel(characterListViewModel: CharactersListViewModel):ViewModel

    @Binds
    @IntoMap
    @com.globant.openbankassignment.di.module.ViewModelKey(CharactersDetailsViewModel::class)
    internal abstract fun bindCharactersDetailsViewModel(characterDetailsViewModel: CharactersDetailsViewModel):ViewModel


    @Binds
    internal abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}