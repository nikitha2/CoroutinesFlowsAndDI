package com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import com.carvana.coroutinesandflows.R
import com.carvana.coroutinesandflows.core.ResourceHolderStates
import com.carvana.coroutinesandflows.databinding.RetrofitWithCnfFragment1Binding
import com.carvana.coroutinesandflows.retrofitWithCoroutinesAndFlows.viewmodel.RetrofitWithCnFViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RetrofitWithCnFFragment1 : Fragment() {
    private val TAG = this::class.java.simpleName
    private lateinit var binding: RetrofitWithCnfFragment1Binding
    @Inject lateinit var retrofitWithCnFViewModel: RetrofitWithCnFViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RetrofitWithCnfFragment1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            setUp()
            setupObservers()
        }
    }

    private fun RetrofitWithCnfFragment1Binding.setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                retrofitWithCnFViewModel.booksResponseModel.collect{uiState->
                    when(uiState){
                        is ResourceHolderStates.Success -> {
                            text.text = uiState.value.random().toString()
                            hideProgressBar()
                        }
                        is ResourceHolderStates.Failed -> { text.text = uiState.exception.message.toString() }
                        is ResourceHolderStates.Loading -> { showProgressBar() }
                        is ResourceHolderStates.Always -> { hideProgressBar() }
                    }
                }
            }
        }

    }

    private fun setUp() {
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.activity2_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        lifecycleScope.launch {
            retrofitWithCnFViewModel.fetchCurrentlyReadingBooks()
        }

        binding.apply {

            nextBtn.setOnClickListener {
                navController.navigate(R.id.action_main2_fragment2)
            }

            BackBtn.setOnClickListener {
                navController.navigate(R.id.action_retroPhotoActivity2)
                activity?.finish()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView called")
    }

    private fun RetrofitWithCnfFragment1Binding.hideProgressBar(){
        progressBar.visibility = View.INVISIBLE
    }
    private fun RetrofitWithCnfFragment1Binding.showProgressBar(){
        progressBar.visibility = View.VISIBLE
    }

    companion object {
        fun newInstance() = RetrofitWithCnFFragment1()
    }
}