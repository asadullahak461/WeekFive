package com.example.weekfive.activities

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.weekfive.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnsignup.setOnClickListener {

            if (TextUtils.isEmpty(binding.name.editText?.text.toString().trim())) {
                binding.name.error = "Name cannot be empty"
            }
            if (TextUtils.isEmpty(binding.email.editText?.text.toString().trim())) {
                binding.email.setError("Email cannot be empty")
            }
            if (TextUtils.isEmpty(binding.password.editText?.text.toString().trim())) {
                binding.password.setError("Password cannot be empty")
            }
            if (TextUtils.isEmpty(binding.gender.editText?.text.toString().trim())) {
                binding.gender.setError("Gender cannot be empty")
            }

        }
    }


}