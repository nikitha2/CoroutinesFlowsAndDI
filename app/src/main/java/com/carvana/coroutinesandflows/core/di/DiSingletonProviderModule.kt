package com.carvana.coroutinesandflows.core.di

import com.carvana.coroutinesandflows.core.utils.Environment
import com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.networkLayer.BooksService
import com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.networkLayer.repository.BooksRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DiSingletonProviderModule {

    @Provides
    @Singleton
    fun provideRetrofitWithBaseUrl(environment: Environment): Retrofit {
        return Retrofit.Builder()
            .baseUrl(environment.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}