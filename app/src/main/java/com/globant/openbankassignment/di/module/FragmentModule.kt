package com.globant.openbankassignment.di.module

import com.globant.openbankassignment.ui.charactersdetails.CharactersDetailsFragment
import com.globant.openbankassignment.ui.characterslist.CharactersListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    internal abstract fun contributeCharactersListFragment(): CharactersListFragment

    @ContributesAndroidInjector
    internal abstract fun contributeCharactersDetailsFragment(): CharactersDetailsFragment
}
