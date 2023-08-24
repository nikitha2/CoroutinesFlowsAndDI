package com.carvana.coroutinesandflows.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.carvana.coroutinesandflows.R
import com.carvana.coroutinesandflows.data.Data
import com.carvana.coroutinesandflows.databinding.MainFragmentBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private val TAG = this::class.java.simpleName
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            setUp()
        }
    }

    private fun MainFragmentBinding.setUp() {
        val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.main_activity_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        Data.buttonsTitles.forEach{ (buttonTitle, action) ->
            val button = Button(activity)
            button.layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
            button.text = buttonTitle
            button.setOnClickListener {
                navController.navigate(action)
            }
            mainLayout.addView(button)
        }
    }

}