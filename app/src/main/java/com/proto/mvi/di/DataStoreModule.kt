package com.proto.mvi.di

// DataStoreModule.kt
import com.proto.mvi.di.providers.DataStoreProvider
import com.proto.mvi.di.repos.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Or ViewModelComponent if scoped to ViewModels
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        dataStoreProvider: DataStoreProvider
    ): DataStoreRepository {
        return DataStoreRepository(dataStoreProvider.dataStore)
    }
}