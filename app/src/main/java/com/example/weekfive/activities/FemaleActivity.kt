package com.example.weekfive.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.weekfive.R
import com.example.weekfive.databinding.ActivityFemaleBinding
import com.example.weekfive.databinding.ActivityMaleBinding

class FemaleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFemaleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFemaleBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var city = arrayOf(
            "Select City",
            "Lahore"

        )
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, city)

        // Set layout to use when the spinner with the list is displayed

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Set Adapter to Spinner

        binding.cityspinner!!.setAdapter(arrayAdapter)
    }
}