package com.example.weekfive.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.weekfive.R
import java.io.File

class advance_frag : Fragment() {
    private lateinit var rootView: View
    lateinit var pic1 : ImageView
    lateinit var pic2 : ImageView
    lateinit var pic3 : ImageView
    lateinit var pic4 : ImageView
    lateinit var cityspinner : Spinner
    lateinit var upload_marksheet : Button
    lateinit var txtpdf : TextView
    private val pic_one = 1
    private val pic_two = 2
    private val pic_three = 3
    private val pic_four = 4
    private val pdf_Code = 5


    // inflate the layout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       rootView =  inflater.inflate(R.layout.fragment_advance, container, false)

        setupUi(rootView)
        return rootView
    }

    private fun setupUi(rootView: View) {
        pic1 = rootView!!.findViewById(R.id.pic1)
        pic2 = rootView!!.findViewById(R.id.pic2)
        pic3 = rootView!!.findViewById(R.id.pic3)
        pic4 = rootView!!.findViewById(R.id.pic4)
        cityspinner = rootView!!.findViewById(R.id.cityspinner)
        upload_marksheet = rootView!!.findViewById(R.id.btn_marksheet)
        txtpdf = rootView!!.findViewById(R.id.txtpdf)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pic1.setOnClickListener {
            val camera_intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            camera_intent.putExtra("android.intent.extras.CAMERA_FACING", 1)
            startActivityForResult(camera_intent, pic_one)
        }
        pic2.setOnClickListener {
            val camera_intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(camera_intent, pic_two)
        }
        pic3.setOnClickListener {
            val camera_intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            camera_intent.putExtra("android.intent.extras.CAMERA_FACING", 1)
            startActivityForResult(camera_intent, pic_three)
        }
        pic4.setOnClickListener {
            val camera_intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(camera_intent, pic_four)
        }
        upload_marksheet.setOnClickListener {
            showFileChooser()

        }

        var city = arrayOf(
            "Select City",
            "Lahore",
            "Karachi",
            "Quetta"

        )
        val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, city)
        // Set layout to use when the spinner with the list is displayed
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Set Adapter to Spinner
        cityspinner!!.setAdapter(arrayAdapter)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == pic_one && resultCode == Activity.RESULT_OK && data != null) {

            // BitMap is data structure of image file which store the image in memory
            val photo = data!!.extras!!["data"] as Bitmap?
            pic1.setImageBitmap(photo)
        }
        else if (requestCode == pic_two && resultCode == Activity.RESULT_OK && data != null) {

            // BitMap is data structure of image file which store the image in memory
            val photo = data!!.extras!!["data"] as Bitmap?
            pic2.setImageBitmap(photo)
        }
        else if (requestCode == pic_three && resultCode == Activity.RESULT_OK && data != null) {

            // BitMap is data structure of image file which store the image in memory
            val photo = data!!.extras!!["data"] as Bitmap?
            pic3.setImageBitmap(photo)
        }
        else if (requestCode == pic_four && resultCode == Activity.RESULT_OK && data != null) {

            // BitMap is data structure of image file which store the image in memory
            val photo = data!!.extras!!["data"] as Bitmap?
            pic4.setImageBitmap(photo)
        }
        else if (requestCode == pdf_Code && resultCode == Activity.RESULT_OK && data != null) {

            val uri: Uri? = data.data
            val path: String = uri?.path.toString()
            val file = File(path)
            txtpdf.text = file.name
        }
    }

    fun showFileChooser() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
//        intent.addCategory(Intent.CATEGORY_OPENABLE)
        try {
            startActivityForResult(Intent.createChooser(intent, ""), pdf_Code)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Try Again!", Toast.LENGTH_SHORT).show()
        }
    }
}

