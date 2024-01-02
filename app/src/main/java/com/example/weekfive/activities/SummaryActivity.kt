package com.example.weekfive.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weekfive.R
import com.example.weekfive.databinding.ActivityLoginBinding
import com.example.weekfive.databinding.ActivitySummaryBinding

class SummaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySummaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}