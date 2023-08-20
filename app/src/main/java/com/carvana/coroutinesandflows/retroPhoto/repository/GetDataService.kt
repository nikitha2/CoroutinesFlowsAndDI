package com.carvana.coroutinesandflows.retroPhoto.repository

import com.carvana.coroutinesandflows.retroPhoto.models.RetroPhotoResponseModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface GetDataService {

    @GET("/photos")
    suspend fun getAllPhotos(): List<RetroPhotoResponseModel>

}