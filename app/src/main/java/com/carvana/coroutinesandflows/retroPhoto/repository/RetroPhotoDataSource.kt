package com.carvana.coroutinesandflows.retroPhoto.repository

import android.util.Log
import com.carvana.coroutinesandflows.retroPhoto.models.RetroPhotoResponseModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RetroPhotoDataSource(dataService: GetDataService) {
    private val refreshIntervalMs = 5000L

    val latestPhotos: Flow<List<RetroPhotoResponseModel>> = flow {
        while(true) {
            val latestPhotos = dataService.getAllPhotos()
            Log.d("RetroPhotoDataSource", "Emitting new photos: $latestPhotos")
            emit(latestPhotos) // Emits the result of the request to the flow
            delay(refreshIntervalMs) // Suspends the coroutine for some time
        }
    }
}
