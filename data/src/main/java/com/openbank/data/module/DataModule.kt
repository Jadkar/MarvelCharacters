package com.openbank.data.module

import com.openbank.data.mapper.CharactersDetailsMapperImpl
import com.openbank.data.mapper.CharacterListMapper
import com.openbank.data.mapper.CharacterListMapperImpl
import com.openbank.data.mapper.CharactersDetailsMapper
import com.openbank.data.repository.GetCharacterDetailsRepositoryImpl
import com.openbank.data.repository.GetCharactersRepositoryImpl
import com.openbank.data.remote.*
import com.openbank.domain.repository.GetCharactersDetailsRepository
import com.openbank.domain.repository.GetCharactersRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideGetCharacterRepo(getCharacterListRemoteSource: GetCharacterListRemoteSource): GetCharactersRepository {
        return GetCharactersRepositoryImpl(getCharacterListRemoteSource)
    }

    @Provides
    @Singleton
    fun provideGetCharacterDetailsRepo(getCharacterDetailsRemoteSource: GetCharacterDetailsRemoteSource): GetCharactersDetailsRepository {
        return GetCharacterDetailsRepositoryImpl(getCharacterDetailsRemoteSource)
    }

    @Provides
    @Singleton
    fun provideGetCharacterListRemoteSource(service: MarvelApi, characterListMapper: CharacterListMapper): GetCharacterListRemoteSource {
        return GetCharacterListRemoteSourceImpl(service,characterListMapper)
    }

    @Provides
    @Singleton
    fun provideGetCharacterDetailsRemoteSource(service: MarvelApi, charactersDetailsMapper: CharactersDetailsMapper): GetCharacterDetailsRemoteSource {
        return GetCharacterDetailRemoteSourceImpl(service,charactersDetailsMapper)
    }

    @Provides
    @Singleton
    fun provideCharacterListMapperImpl(): CharacterListMapper {
        return CharacterListMapperImpl()
    }

    @Provides
    @Singleton
    fun provideCharacterDetailsMapperImpl(): CharactersDetailsMapper {
        return CharactersDetailsMapperImpl()
    }


}