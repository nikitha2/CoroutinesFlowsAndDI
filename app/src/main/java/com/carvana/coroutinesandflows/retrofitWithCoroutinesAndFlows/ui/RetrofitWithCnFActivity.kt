package com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.carvana.coroutinesandflows.databinding.RetrofitWithCnfActivityBinding

/**
 *
 * Retrofit with Flows and coroutines
 * https://medium.com/android-tech-stack/retrofit-with-coroutine-flows-using-mvvm-f0eb397bfc0a
 *
 */
class RetrofitWithCnFActivity : AppCompatActivity() {
    private lateinit var binding: RetrofitWithCnfActivityBinding
    private val TAG = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RetrofitWithCnfActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroyView called")
    }
}