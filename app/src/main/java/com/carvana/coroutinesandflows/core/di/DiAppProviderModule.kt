package com.carvana.coroutinesandflows.core.di

import com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.networkLayer.BooksService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class) //Scoping to viewmodel so that instance is saved across configuration changes
//@InstallIn(ActivityRetainedComponent::class) // To scope to activity
class DiAppProviderModule {

    @Provides
    @ViewModelScoped //Scoping to viewmodel so that instance is saved across configuration changes
//  @ActivityRetainedScoped // To scope to activity
    fun provideBooksService(retrofit: Retrofit): BooksService {
        return retrofit.create(BooksService::class.java)
    }
}