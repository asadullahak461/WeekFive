package com.example.weekfive.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.weekfive.R
import com.example.weekfive.ViewPagerAdapter
import com.example.weekfive.databinding.ActivityAdvanceFormBinding
import com.example.weekfive.databinding.ActivityLoginBinding
import com.example.weekfive.fragments.activities_frag
import com.example.weekfive.fragments.education_frag
import com.example.weekfive.fragments.sports_frag
import com.google.android.material.tabs.TabLayout

class AdvanceFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdvanceFormBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdvanceFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initializing the ViewPagerAdapter
        val adapter = ViewPagerAdapter(supportFragmentManager)

        // add fragment to the list
        adapter.addFragment(activities_frag(), "Basic")
        adapter.addFragment(education_frag(), "Advanced")
        adapter.addFragment(sports_frag(), "Pro")

        // Adding the Adapter to the ViewPager
        binding.viewPager.adapter = adapter

        // bind the viewPager with the TabLayout.
        binding.tabs.setupWithViewPager(binding.viewPager)
    }

}