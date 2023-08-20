package com.carvana.coroutinesandflows.retroPhoto.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.carvana.coroutinesandflows.databinding.ActivityRetroPhotoBinding

class RetroPhotoActivity : AppCompatActivity() {
    private val TAG = this::class.java.simpleName

    private lateinit var binding: ActivityRetroPhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetroPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroyView called")
    }
}