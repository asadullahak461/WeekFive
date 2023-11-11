package com.example.weekfive.activities

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.weekfive.databinding.ActivitySignupBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    // create Firebase authentication object
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialising auth object
        auth = Firebase.auth

        binding.btnsignup.setOnClickListener {
            binding.spinKit.visibility= View.VISIBLE

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
//            SignUp
            auth.createUserWithEmailAndPassword(binding.email.editText?.text.toString().trim(),binding.password.editText?.text.toString().trim()).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Successfully Singed Up", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
                }
            }
            binding.spinKit.visibility= View.GONE

        }
    }


}