package com.carvana.coroutinesandflows.core

import android.view.View.INVISIBLE
import android.view.View.VISIBLE

// Represents different states for the LatestNews screen
sealed class ResourceHolderStates {
    data class Success(val value: Any): ResourceHolderStates()
    data class Loading(val progressBarVisibility: Int = VISIBLE): ResourceHolderStates()
    data class Failed(val exception: Throwable): ResourceHolderStates()
    data class Always(val progressBarVisibility: Int = INVISIBLE): ResourceHolderStates()
}

