package com.example.weekfive

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.weekfive.activities.LoginActivity
import com.example.weekfive.activities.OptionActivity
import com.example.weekfive.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        //Normal Handler is deprecated , so we have to change the code little bit

        // Start the main activity after a delay
        Handler().postDelayed({
            checkLogged()

        }, 2000)
    }

    fun checkLogged() {
        val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        var status = sharedPreferences.getString("isLoginIn", "N/A");
        if (status == "true") {
            startActivity(Intent(this, OptionActivity::class.java))
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        finish()
    }
}

