package com.hgshkt.androidtask5.app.di

import com.hgshkt.androidtask5.data.api.ApiClient
import com.hgshkt.androidtask5.data.api.ApiInterface
import com.hgshkt.androidtask5.data.repository.SuperHeroRepository
import com.hgshkt.androidtask5.data.repository.SuperHeroRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun getApiClient(): ApiClient = ApiClient()

    @Provides
    @Singleton
    fun getRepository(apiClient: ApiClient): SuperHeroRepository {
        return SuperHeroRepositoryImpl(apiClient.client.create(ApiInterface::class.java))
    }
}