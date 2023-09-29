package com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carvana.coroutinesandflows.core.ResourceHolderStates
import com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.networkLayer.repository.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RetrofitWithCnFViewModel @Inject constructor(
    private val booksRepository: BooksRepository
) : ViewModel() {

    private val _booksResponseModel =
        MutableStateFlow<ResourceHolderStates>(ResourceHolderStates.Loading())
    var booksResponseModel: StateFlow<ResourceHolderStates> = _booksResponseModel

    suspend fun fetchCurrentlyReadingBooks() {
        //Cancels the scope automatically for you in the ViewModel's onCleared() method
        viewModelScope.launch {
            booksRepository.fetchCurrentlyReadingBooks()
                // Intermediate catch operator. If an exception is thrown,catch and update the UI
                .catch { exception -> notifyError(_booksResponseModel, exception) }
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

}