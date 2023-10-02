package com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.networkLayer.repository

import android.util.Log
import com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.models.BooksResponseModel
import com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.networkLayer.BooksService
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface BooksRepository {
    fun fetchCurrentlyReadingBooks(): Flow<BooksResponseModel>
}

class BooksRepositoryImpl @Inject constructor(
    private val booksService: BooksService
) : BooksRepository {
    override fun fetchCurrentlyReadingBooks(): Flow<BooksResponseModel> = flow {
        val response = booksService.fetchCurrentlyReadingBooks()
        Log.d( "BooksRepositoryImpl", "$response")
        emit(response)
    }
}