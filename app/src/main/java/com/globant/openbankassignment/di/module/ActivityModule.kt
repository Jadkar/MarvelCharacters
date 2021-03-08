package com.globant.openbankassignment.di.module

import com.globant.openbankassignment.ui.characterslist.CharactersListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributesCharactersListActivity():CharactersListActivity
}