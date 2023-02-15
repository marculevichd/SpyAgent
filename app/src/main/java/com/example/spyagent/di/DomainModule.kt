package com.example.spyagent.di

import com.example.spyagent.domain.MainMenuInteractor
import com.example.spyagent.domain.MainMenuRepository
import com.example.spyagent.domain.StartNavigationInteractor
import com.example.spyagent.domain.StartNavigationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideStartNavigationInteractor(startNavigationRepository: StartNavigationRepository): StartNavigationInteractor {
        return StartNavigationInteractor(startNavigationRepository)
    }

    @Provides
    fun provideMainMenuInteractor(mainMenuRepository: MainMenuRepository): MainMenuInteractor {
        return MainMenuInteractor(mainMenuRepository)
    }

}