package com.example.weekfive.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weekfive.ViewPagerAdapter
import com.example.weekfive.databinding.ActivityAdvanceFormBinding
import com.example.weekfive.fragments.basic_frag
import com.example.weekfive.fragments.advance_frag
import com.example.weekfive.fragments.pro_frag

class AdvanceFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdvanceFormBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdvanceFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initializing the ViewPagerAdapter
        val adapter = ViewPagerAdapter(supportFragmentManager)

        // add fragment to the list
        adapter.addFragment(basic_frag(), "Basic")
        adapter.addFragment(advance_frag(), "Advanced")
        adapter.addFragment(pro_frag(), "Pro")

        // Adding the Adapter to the ViewPager
        binding.viewPager.adapter = adapter

        // bind the viewPager with the TabLayout.
        binding.tabs.setupWithViewPager(binding.viewPager)
    }

}