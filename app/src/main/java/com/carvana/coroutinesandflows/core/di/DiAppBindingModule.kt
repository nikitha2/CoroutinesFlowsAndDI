package com.carvana.coroutinesandflows.core.di

import com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.networkLayer.repository.BooksRepository
import com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.networkLayer.repository.BooksRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DiAppBindingModule {

    @Binds
    @Singleton
    abstract fun bindBooksRepository(
        booksRepositoryImpl: BooksRepositoryImpl
    ): BooksRepository

}