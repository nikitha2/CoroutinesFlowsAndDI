package com.carvana.coroutinesandflows.retroPhoto.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.NavHostFragment
import com.carvana.coroutinesandflows.R
import com.carvana.coroutinesandflows.core.ResourceHolderStates.*
import com.carvana.coroutinesandflows.databinding.RetroPhotoFragmentBinding
import com.carvana.coroutinesandflows.retroPhoto.viewmodel.RetroPhotoViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class RetroPhotoFragment : Fragment() {
    private val TAG = this::class.java.simpleName

    private lateinit var binding: RetroPhotoFragmentBinding
    private val retroPhotoViewModel: RetroPhotoViewModel by viewModels { RetroPhotoViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RetroPhotoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            setUp()
            setupObservers()
        }
    }

    private fun RetroPhotoFragmentBinding.setupObservers() {

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                retroPhotoViewModel.retroPhotoResponseModel1.collect { uiState ->
                    Log.d("UIStateCollector", "New UI state received: $uiState")
                    text.text = uiState.random().toString()
                    hideProgressBar()
                }
            }
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("onViewStateRestored", "onViewStateRestored called")
    }

    fun RetroPhotoFragmentBinding.hideProgressBar() {
        progressBar.visibility = INVISIBLE
    }

    fun RetroPhotoFragmentBinding.showProgressBar() {
        progressBar.visibility = VISIBLE
    }

    private fun setUp() {
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.apply {
            lifecycleScope.launch {
                retroPhotoViewModel.getAllPhotos()
            }

            nextBtn.setOnClickListener {
                navController.navigate(R.id.action_clickNextBtn)
            }
        }

    }

    override fun onDetach() {
        super.onDetach()
        lifecycleScope.cancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView called")
    }

    companion object {
        fun newInstance() = RetroPhotoFragment()
    }
}