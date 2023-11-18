package com.example.weekfive.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    lateinit var gender : Array<String>
    private lateinit var binding: ActivityOptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionBinding.inflate(layoutInflater)
        setContentView(binding.root)




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
        getUserData()
    }
    private fun getUserData() {

        for (i in image.indices){

            val items = items(image[i],gender[i])
            newArrayList.add(items)
        }

        recyclerView.adapter = CustomAdapter(newArrayList)
    }
}