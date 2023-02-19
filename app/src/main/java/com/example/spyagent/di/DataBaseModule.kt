package com.example.spyagent.di

import android.content.Context
import com.example.spyagent.data.database.sets.DataBaseSets
import com.example.spyagent.data.database.sets.SetsDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    fun provideDataBaseSets(context: Context): DataBaseSets {
        return DataBaseSets.getDataBaseSetsInstance(context)
    }

    @Provides
    fun provideSetsDAO(dataBaseSets: DataBaseSets): SetsDAO {
        return dataBaseSets.getSetsDAO()
    }

}