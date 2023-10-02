package com.carvana.coroutinesandflows.core.di

import com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.networkLayer.repository.BooksRepository
import com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.networkLayer.repository.BooksRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
//@InstallIn(ActivityRetainedComponent::class) // To scope to activity

abstract class DiAppBindingModule {


    /** As BooksRepository is injected in viewmodel. It can be inject be
     * scope: unscoped, or scoped to ActivityRetainedComponent or ApplicationComponent.
     * I want my repository to be scoped to activity. So using @ActivityRetainedScoped
     * */
    @Binds
    @ViewModelScoped //Scoping to viewmodel so that instance is saved across configuration changes
//  @ActivityRetainedScoped // To scope to activity
    abstract fun bindBooksRepository(
        booksRepositoryImpl: BooksRepositoryImpl
    ): BooksRepository

}