package com.example.spyagent.di

import android.content.Context
import com.example.spyagent.data.ApiService
import com.example.spyagent.data.MainMenuRepositoryImpl
import com.example.spyagent.data.SharedPreferencesHelper
import com.example.spyagent.data.StartNavigationRepositoryImpl
import com.example.spyagent.domain.MainMenuRepository
import com.example.spyagent.domain.StartNavigationRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindStartNavigationRepository(startNavigationRepositoryImpl: StartNavigationRepositoryImpl): StartNavigationRepository

    @Binds
    abstract fun bindMainMenuRepository(mainMenuRepositoryImpl: MainMenuRepositoryImpl): MainMenuRepository


    companion object {
        private const val SP_KEY = "SP_KEY"

        @Provides
        fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferencesHelper {
            return SharedPreferencesHelper(
                context.getSharedPreferences(
                    SP_KEY,
                    Context.MODE_PRIVATE
                )
            )
        }

        private const val BASE_URL = "https://api.jsonserve.com"

        @Provides
        fun provideApiService(retrofit: Retrofit): ApiService {
            return retrofit.create(ApiService::class.java)
        }

        @Provides
        fun provideRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }


}