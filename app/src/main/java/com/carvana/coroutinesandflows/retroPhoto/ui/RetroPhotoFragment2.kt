package com.carvana.coroutinesandflows.retroPhoto.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.carvana.coroutinesandflows.R
import com.carvana.coroutinesandflows.activity2.ui.MainActivity2
import com.carvana.coroutinesandflows.databinding.FragmentRetroPhoto2Binding
import com.carvana.coroutinesandflows.databinding.FragmentRetroPhotoBinding
import com.carvana.coroutinesandflows.retroPhoto.viewmodel.RetroPhotoViewModel
import kotlinx.coroutines.launch

class RetroPhotoFragment2 : Fragment() {
    private val TAG = this::class.java.simpleName
    private lateinit var binding: FragmentRetroPhoto2Binding
    //private val retroPhotoViewModel: RetroPhotoViewModel by viewModels { RetroPhotoViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRetroPhoto2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }

    private fun init() {
        val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.apply {

            backBtn.setOnClickListener{
                navController.popBackStack()
                //navController.navigate(R.id.action_clickBackBtn)
            }

            nextActivityBtn.setOnClickListener{
                navController.navigate(R.id.action_nextActivityBtn)
                activity?.finish()   // This will finish the activity. Hence viewModel associated will also get destroyed
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView called")
    }

    companion object {
        fun newInstance() = RetroPhotoFragment2()
    }
}