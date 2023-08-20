package com.carvana.coroutinesandflows.retroPhoto.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.carvana.coroutinesandflows.R
import com.carvana.coroutinesandflows.databinding.FragmentRetroPhotoBinding
import com.carvana.coroutinesandflows.retroPhoto.viewmodel.RetroPhotoViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RetroPhotoFragment : Fragment() {
    private val TAG = this::class.java.simpleName

    private lateinit var binding: FragmentRetroPhotoBinding
    private val retroPhotoViewModel: RetroPhotoViewModel by viewModels { RetroPhotoViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRetroPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            setUp()
            setupObservers()
        }
    }

    private fun FragmentRetroPhotoBinding.setupObservers() {
        lifecycleScope.launch {
            retroPhotoViewModel.retroPhotoResponseModel.collect {
                if(it.isNotEmpty())
                    text.text = it.random().toString()
            }
        }
    }

    private fun setUp() {
        val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.apply {
            lifecycleScope.launch {
                retroPhotoViewModel.getAllPhotos()
            }

            nextBtn.setOnClickListener{
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