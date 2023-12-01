package com.example.weekfive.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weekfive.CustomAdapter
import com.example.weekfive.R
import com.example.weekfive.databinding.ActivityOptionBinding
import com.example.weekfive.items


class OptionActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<items>
    lateinit var image: Array<Int>
    lateinit var gender: Array<String>
    private lateinit var binding: ActivityOptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.maintoolbar);

        getUserData()

    }

    private fun getUserData() {
        val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        binding.tvUser.text = "Hello ! ${sharedPreferences.getString("userEmail", "N/A")}";

        image = arrayOf(

            R.drawable.male,
            R.drawable.female
        )
        gender = arrayOf(
            "Male",
            "Female"
        )
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<items>()

        for (i in image.indices) {

            val items = items(image[i], gender[i])
            newArrayList.add(items)
        }

        recyclerView.adapter = CustomAdapter(newArrayList)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.logout -> {
                val preferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
                val editor = preferences.edit()
                editor.clear()
                editor.apply()
                intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finishAffinity()
                return true

            }
        }
        return true
    }
}