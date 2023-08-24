package com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.networkLayer

import com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.models.BooksResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface BookApi {
    fun fetchCurrentlyReadingBooks(): Flow<List<BooksResponseModel>>
}


class BookApiImp(private val booksService: BooksService): BookApi{
    override fun fetchCurrentlyReadingBooks(): Flow<List<BooksResponseModel>> = flow {
        emit(booksService.fetchCurrentlyReadingBooks())
    }

}