package com.carvana.coroutinesandflows.retroPhoto.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.carvana.coroutinesandflows.RetrofitClientInstance
import com.carvana.coroutinesandflows.retroPhoto.models.RetroPhotoResponseModel
import com.carvana.coroutinesandflows.retroPhoto.repository.RetroPhotoDataSource
import com.carvana.coroutinesandflows.retroPhoto.repository.RetroPhotoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class RetroPhotoViewModel(private val retroPhotoRepository: RetroPhotoRepository) : ViewModel() {

  //  var retroPhotoResponseModel: MutableLiveData<List<RetroPhotoResponseModel>> = MutableLiveData()

    private val _retroPhotoResponseModel = MutableStateFlow<List<RetroPhotoResponseModel>>(emptyList())
    var retroPhotoResponseModel: StateFlow<List<RetroPhotoResponseModel>> = _retroPhotoResponseModel


    suspend fun getAllPhotos() {
        //Cancels the scope automatically for you in the ViewModel's onCleared() method
        viewModelScope.launch {
            retroPhotoRepository.getAllPhotos()
                // Intermediate catch operator. If an exception is thrown,catch and update the UI
                .catch { exception -> notifyError(exception) }
                .collect { latestPhotos ->
                    // Update View with the latestPhotos
                    _retroPhotoResponseModel.value = latestPhotos
                }

        }
    }

    private fun notifyError(exception: Throwable) {
        Log.d("RetroPhotoViewModel",exception.message.toString())
        _retroPhotoResponseModel.value = listOf(RetroPhotoResponseModel(title = exception.message.toString()))
    }


    override fun onCleared() {
        super.onCleared()
        Log.d("RetroPhotoViewModel", "--------onCleared called-----------")
        //viewModelScope canceled here
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