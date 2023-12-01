package com.example.weekfive.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
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

            binding.spinKit.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed(5000) {



                if (TextUtils.isEmpty(binding.email.editText?.text.toString().trim())) {
                    binding.email.error = "Email cannot be empty"
                }
                if (TextUtils.isEmpty(binding.password.editText?.text.toString().trim())) {
                    binding.password.error = "Password cannot be empty"
                }

//            Login
                auth.signInWithEmailAndPassword(
                    binding.email.editText?.text.toString().trim(),
                    binding.password.editText?.text.toString().trim()
                ).addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        binding.spinKit.visibility = View.GONE
                        Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
                        isLogin("true",binding.email.editText?.text.toString().trim(),
                            binding.password.editText?.text.toString().trim())


                        intent = Intent(this, OptionActivity::class.java)
                        startActivity(intent)
                    } else
                        Toast.makeText(this, "User Not Found", Toast.LENGTH_SHORT).show()
                    binding.spinKit.visibility = View.GONE
                }

                }

            binding.txtsignup.setOnClickListener {
                intent = Intent(this, SignupActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }
    fun isLogin(status:String,email:String,password:String){
        // Creating a shared pref object with a file name "MySharedPref" in private mode
        val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()

        // write all the data entered by the user in SharedPreference and apply
        myEdit.putString("isLoginIn", status)
        myEdit.putString("userEmail", email)
        myEdit.putString("userpassword",password)
        myEdit.apply()
    }
}