package com.example.weekfive.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weekfive.R
import com.example.weekfive.databinding.ActivityLoginBinding
import com.example.weekfive.databinding.ActivitySummaryBinding
import com.example.weekfive.room.AppDatabase
import com.example.weekfive.room.DatabaseBuilder
import com.example.weekfive.room.dao

class SummaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySummaryBinding
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        database = DatabaseBuilder.getInstance(this)
        val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        binding.email.text = "${sharedPreferences.getString("userEmail", "N/A")}";

        binding.userpassword.text = "${sharedPreferences.getString("userpassword", "N/A")}";


        binding.malecount.text = database.dao().getMaleCount().toString()
        binding.femalecount.text = database.dao().getFemaleCount().toString()
        binding.basiccount.text = database.dao().getBasicCount().toString()
        binding.advancecount.text = database.dao().getAdvanceCount().toString()
        binding.procount.text = database.dao().getProCount().toString()
        binding.totalcount.text =
            (database.dao().getBasicCount() + database.dao().getAdvanceCount() +
                    database.dao().getProCount()).toString()
    }
}