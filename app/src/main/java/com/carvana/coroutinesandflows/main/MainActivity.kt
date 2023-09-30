package com.carvana.coroutinesandflows.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.carvana.coroutinesandflows.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    private val TAG = this::class.java.simpleName
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroyView called")
    }
}