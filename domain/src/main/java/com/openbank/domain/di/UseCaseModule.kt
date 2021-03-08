package com.openbank.domain.di


import com.openbank.domain.repository.GetCharactersDetailsRepository

import com.openbank.domain.repository.GetCharactersRepository
import com.openbank.domain.usecase.MarvelCharacterDetailsUseCaseImpl
import com.openbank.domain.usecase.MarvelCharactersDetailsUseCase
import com.openbank.domain.usecase.MarvelCharactersListUseCase
import com.openbank.domain.usecase.MarvelCharactersListUseCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideMarvelCharactersListUseCase(
        repository: GetCharactersRepository
    ): MarvelCharactersListUseCase {
        return MarvelCharactersListUseCaseImpl(repository)
    }
    @Provides
    @Singleton
    fun provideMarvelCharactersDetailsUseCase(
        repository: GetCharactersDetailsRepository
        ): MarvelCharactersDetailsUseCase {
        return MarvelCharacterDetailsUseCaseImpl(repository)
    }
}