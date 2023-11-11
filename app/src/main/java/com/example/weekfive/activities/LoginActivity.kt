package com.example.weekfive.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.weekfive.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initialising Firebase auth object
        auth = FirebaseAuth.getInstance()


        binding.btnlogin.setOnClickListener {

            binding.spinKit.visibility= View.VISIBLE

            if (TextUtils.isEmpty(binding.email.editText?.text.toString().trim())) {
                binding.email.error = "Email cannot be empty"
            }
            if (TextUtils.isEmpty(binding.email.editText?.text.toString().trim())) {
                binding.email.error = "Email cannot be empty"
            }
            if (TextUtils.isEmpty(binding.password.editText?.text.toString().trim())) {
                binding.password.setError("Password cannot be empty")
            }

//            Login
            auth.signInWithEmailAndPassword(binding.email.editText?.text.toString().trim(), binding.password.editText?.text.toString().trim()).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
                } else
                    Toast.makeText(this, "User Not Found", Toast.LENGTH_SHORT).show()
            }
            binding.spinKit.visibility= View.GONE
        }

        binding.txtsignup.setOnClickListener {
            intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

    }
}