package com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.networkLayer.repository

import com.carvana.coroutinesandflows.core.di.DiSingletonBindingModule
import com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.models.BooksResponseModel
import com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.networkLayer.BooksService
import dagger.hilt.InstallIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface BooksRepository {
    fun fetchCurrentlyReadingBooks(): Flow<List<BooksResponseModel>>
}

class BooksRepositoryImpl @Inject constructor(
    private val booksService: BooksService
) : BooksRepository {
    override fun fetchCurrentlyReadingBooks(): Flow<List<BooksResponseModel>> = flow {
        emit(booksService.fetchCurrentlyReadingBooks())
    }
}