package com.hsinha610.daznassignmenths.ui.Screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hsinha610.daznassignmenths.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initHomeFragment()
    }

    private fun initHomeFragment() {
        val homeFragment = HomeFragment.newInstance()
        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentContainerViewTag.id, homeFragment)
            commit()
        }
    }
}