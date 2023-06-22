package com.hsinha610.daznassignmenths.ui.Screens

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hsinha610.daznassignmenths.databinding.ActivityMainBinding
import com.hsinha610.daznassignmenths.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewmodel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initHomeFragment()
        initViewModelObservers()
    }

    private fun initHomeFragment() {
        val homeFragment = HomeFragment.newInstance()
        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentContainerViewTag.id, homeFragment)
            commit()
        }
    }

    private fun initViewModelObservers() {
        viewmodel.openDetailFragmentLD.observe(this) { selectedItemIndex ->
            selectedItemIndex?.let {
                openDetailFragment(selectedItemIndex)
            }
        }
    }

    private fun openDetailFragment(selectedItemIndex: Int) {
        val detailFragment =
            DetailFragment.newInstance(selectedItemIndex = selectedItemIndex)
        supportFragmentManager.beginTransaction().apply {
            addToBackStack(DetailFragment.TAG)
            replace(binding.fragmentContainerViewTag.id, detailFragment)
            commit()
        }
    }
}