package com.example.weekfive.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.weekfive.R
import com.example.weekfive.databinding.ActivityFemaleBinding
import com.example.weekfive.databinding.ActivityMaleBinding
import com.example.weekfive.room.AppDatabase
import com.example.weekfive.room.DatabaseBuilder
import com.example.weekfive.room.femaleData

class FemaleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFemaleBinding
    private lateinit var database: AppDatabase
    private val pic_id = 123
    private var adult : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFemaleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = DatabaseBuilder.getInstance(this)


        var city = arrayOf(
            "Select City",
            "Lahore"

        )
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, city)

        // Set layout to use when the spinner with the list is displayed

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Set Adapter to Spinner

        binding.cityspinner!!.setAdapter(arrayAdapter)

        binding.profileImg.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, pic_id)
        }
        binding.btPickDate.setOnClickListener {
            openDatePicker()
            Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show()
        }

        binding.btnSubmitf.setOnClickListener {
            val image: String =binding.profileImg.toString()
            val name : String = binding.etname.text.toString()
            val email: String =binding.etemail.text.toString()
            val password:String =binding.etpassword.text.toString()
            val city : String = binding.cityspinner.toString()
            val dob: String = binding.tvDate.toString()
            if (binding.yes.isChecked){
               adult=true
            }
            try {
                val myObj =
                    femaleData(image = image, name = name, email = email, password = password, city = city,dob=dob
                        ,aboveEighteen = adult)
                database.dao().insertFemale(myObj)



            } catch (e: Exception) {
                Toast.makeText(this, "Same Date Cannot Be Added", Toast.LENGTH_SHORT).show()
            }

        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pic_id) {
            // BitMap is data structure of image file which store the image in memory
            val photo = data!!.extras!!["data"] as Bitmap?
            // Set the image in imageview for display
            binding.profileImg.setImageBitmap(photo)

        }
    }
    private fun openDatePicker() {
        val datePickerDialog = DatePickerDialog(
            this,
            R.style.DialogTheme,
            DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                binding.tvDate.text = "$year.${month + 1}.$day"
            },
            2023,
            0,
            20
        )
        datePickerDialog.show()
    }

}