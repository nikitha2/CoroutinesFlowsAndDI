package com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.carvana.coroutinesandflows.RetrofitClientInstance
import com.carvana.coroutinesandflows.core.ResourceHolderStates
import com.carvana.coroutinesandflows.retroPhoto.repository.RetroPhotoDataSource
import com.carvana.coroutinesandflows.retroPhoto.repository.RetroPhotoRepository
import com.carvana.coroutinesandflows.retroPhoto.viewmodel.RetroPhotoViewModel
import com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.networkLayer.repository.BooksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class RetrofitWithCnFViewModel(private val booksRepository: BooksRepository) : ViewModel() {
    private val _booksResponseModel = MutableStateFlow<ResourceHolderStates>(ResourceHolderStates.Loading())
    var booksResponseModel: StateFlow<ResourceHolderStates> = _booksResponseModel

    suspend fun fetchCurrentlyReadingBooks() {
        //Cancels the scope automatically for you in the ViewModel's onCleared() method
        viewModelScope.launch {
            booksRepository.fetchCurrentlyReadingBooks()
                // Intermediate catch operator. If an exception is thrown,catch and update the UI
                .catch { exception -> notifyError(_booksResponseModel,exception) }
                .collect { latestPhotos ->
                    // Update View with the latestPhotos
                    _booksResponseModel.value = ResourceHolderStates.Success(latestPhotos)
                }
        }
    }

    private fun notifyError(
        _responseModel: MutableStateFlow<ResourceHolderStates>,
        exception: Throwable
    ) {
        _responseModel.value = ResourceHolderStates.Failed(exception)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myRepository = BooksRepository()
                RetrofitWithCnFViewModel(
                    booksRepository = myRepository,
                )
            }
        }
    }
}