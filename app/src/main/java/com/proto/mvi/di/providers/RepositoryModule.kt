package com.proto.mvi.di.providers

import android.content.Context
import com.proto.mvi.data.remote.GithubService
import com.proto.mvi.data.repository.GithubRepository
import com.proto.mvi.network.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGithubRepository(service: GithubService): GithubRepository {
        return GithubRepository(service)
    }

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper {
        return NetworkHelper(context)
    }
}

