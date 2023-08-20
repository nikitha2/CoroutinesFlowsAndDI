package com.carvana.coroutinesandflows.retroPhoto.repository

import com.carvana.coroutinesandflows.retroPhoto.models.RetroPhotoResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


class RetroPhotoRepository(private val newsRemoteDataSource: RetroPhotoDataSource,) {

    // Intermediaries can use intermediate operators to modify the stream of data without consuming the values.
    // filters all nonNull photos and returns
    suspend fun getAllPhotos(): Flow<List<RetroPhotoResponseModel>> =
        newsRemoteDataSource.latestPhotos
            .map {
                    photo -> photo.filter { checkNotNull(true) }
            }
            // catch can also emit items to the flow. The example repository layer could emit the cached values instead.
            // If an error happens, emit an empty list
            .catch {
                    exception -> emit(listOf(RetroPhotoResponseModel(title = exception.message.toString())))
            }
}