package com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.carvana.coroutinesandflows.R
import com.carvana.coroutinesandflows.databinding.RetrofitWithCnfFragment2Binding

class RetrofitWithCnFFragment2 : Fragment() {
    private val TAG = this::class.java.simpleName
    private lateinit var binding: RetrofitWithCnfFragment2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RetrofitWithCnfFragment2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            setUp()
            setupObservers()
        }
    }

    private fun RetrofitWithCnfFragment2Binding.setupObservers() {

    }

    private fun setUp() {
        val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.activity2_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.apply {

            text.text = TAG
            nextBtn.text =getString(R.string.go_back_to_retro_photo_activity)
            nextBtn.setOnClickListener{
                navController.navigate(R.id.action_retroPhotoActivity2)
                activity?.finish()
            }

            BackBtn.visibility = View.GONE
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView called")
    }

    companion object {
        fun newInstance() = RetrofitWithCnFFragment2()
    }
}