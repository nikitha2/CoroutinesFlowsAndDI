package com.carvana.coroutinesandflows.retroPhoto.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RetroPhotoResponseModel(
    @SerializedName("albumId")
    private val albumId: Int = -1,
    @SerializedName("id")
    private val id: Int = -1,
    @SerializedName("title")
    private val title: String = "No Title",
    @SerializedName("url")
    private val url: String = "No Url",
    @SerializedName("thumbnailUrl")
    private val thumbnailUrl: String = "No ThumbnailUrl"
): Serializable