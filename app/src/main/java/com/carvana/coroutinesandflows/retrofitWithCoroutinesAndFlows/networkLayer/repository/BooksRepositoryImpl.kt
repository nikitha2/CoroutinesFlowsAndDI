package com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.networkLayer.repository

import com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.models.BooksResponseModel
import com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.networkLayer.BookApi
import kotlinx.coroutines.flow.Flow

interface BooksRepository {
    fun fetchCurrentlyReadingBooks(): Flow<List<BooksResponseModel>>
}

class BooksRepositoryImpl(private val bookApi: BookApi):BooksRepository {
    override fun fetchCurrentlyReadingBooks(): Flow<List<BooksResponseModel>> {
        return bookApi.fetchCurrentlyReadingBooks()
    }
}