package com.carvana.coroutinesandflows.core.di

import com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.networkLayer.BooksService
import com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.networkLayer.repository.BooksRepository
import com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.networkLayer.repository.BooksRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
object DiAppProviderModule {

    @Provides
    fun provideBooksService(retrofit: Retrofit): BooksService {
        return retrofit.create(BooksService::class.java)
    }

}