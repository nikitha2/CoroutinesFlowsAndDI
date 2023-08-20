package com.carvana.coroutinesandflows.activity2.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.carvana.coroutinesandflows.R
import com.carvana.coroutinesandflows.databinding.FragmentMain2Binding
import kotlinx.coroutines.cancel

class Main2Fragment : Fragment() {
    private val TAG = this::class.java.simpleName
    private lateinit var binding: FragmentMain2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMain2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            setUp()
            setupObservers()
        }
    }

    private fun FragmentMain2Binding.setupObservers() {

    }

    private fun setUp() {
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.activity2_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

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

    companion object {
        fun newInstance() = Main2Fragment()
    }
}