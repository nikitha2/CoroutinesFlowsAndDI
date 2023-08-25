package com.carvana.coroutinesandflows.retroPhoto.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.carvana.coroutinesandflows.RetrofitClientInstance
import com.carvana.coroutinesandflows.core.ResourceHolderStates
import com.carvana.coroutinesandflows.core.ResourceHolderStates.*
import com.carvana.coroutinesandflows.retroPhoto.models.RetroPhotoResponseModel
import com.carvana.coroutinesandflows.retroPhoto.repository.RetroPhotoDataSource
import com.carvana.coroutinesandflows.retroPhoto.repository.RetroPhotoRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RetroPhotoViewModel(private val retroPhotoRepository: RetroPhotoRepository) : ViewModel() {

    private val _retroPhotoResponseModel1 = MutableSharedFlow<List<RetroPhotoResponseModel>>()
    var retroPhotoResponseModel1: SharedFlow<List<RetroPhotoResponseModel>> = _retroPhotoResponseModel1

    suspend fun getAllPhotos() {
        //Cancels the scope automatically for you in the ViewModel's onCleared() method
        viewModelScope.launch {
            retroPhotoRepository.getAllPhotos()
                // Intermediate catch operator. If an exception is thrown,catch and update the UI
                .catch { exception -> notifyError(exception) }
                .collect { latestPhotos ->
                    // Update View with the latestPhotos
                    Log.d("RetroPhotoViewModel", "Received new photos: $latestPhotos")
                    _retroPhotoResponseModel1.emit(latestPhotos)
                }
        }
    }

    private suspend fun notifyError(exception: Throwable) {
        Log.d("RetroPhotoViewModel",exception.message.toString())
        _retroPhotoResponseModel1.emit(emptyList())
    }


    override fun onCleared() {
        super.onCleared()
        Log.d("RetroPhotoViewModel", "--------onCleared called-----------")
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myRepository =
                    RetroPhotoRepository(RetroPhotoDataSource(RetrofitClientInstance.getDataService))
                RetroPhotoViewModel(
                    retroPhotoRepository = myRepository,
                )
            }
        }
    }
}