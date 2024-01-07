package com.example.weekfive.fragments

import android.R.attr
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.hardware.camera2.CameraCharacteristics
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.weekfive.R
import com.example.weekfive.room.AppDatabase
import com.example.weekfive.room.DatabaseBuilder
import com.example.weekfive.room.basic
import com.example.weekfive.room.maleData
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.Base64


class basic_frag : Fragment() {
    private lateinit var database: AppDatabase
    private lateinit var rootView: View
    lateinit var submit_btn : Button
    lateinit var btnpdf: Button
    lateinit var tv_pdfname: TextView
    lateinit var front_pic: ImageView
    lateinit var back_pic: ImageView
    lateinit var nicnum : EditText
    lateinit var phonenum : EditText
    private val pic_one = 1
    private val pic_two = 2
    private val pdf_Code = 3
    lateinit var photo1:Bitmap
    lateinit var photo2:Bitmap



    // inflate the layout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_basic, container, false)

        setupUi(rootView)

        return rootView
    }

    private fun setupUi(rootView: View?) {
        database = DatabaseBuilder.getInstance(requireContext())
        btnpdf = rootView!!.findViewById(R.id.btnpdf)
        tv_pdfname = rootView!!.findViewById(R.id.txtpdf)
        front_pic = rootView!!.findViewById(R.id.front_pic)
        back_pic = rootView!!.findViewById(R.id.back_pic)
        submit_btn = rootView!!.findViewById(R.id.submit_btn)
        nicnum = rootView!!.findViewById(R.id.nicnum)
        phonenum = rootView!!.findViewById(R.id.phonenum)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btnpdf.setOnClickListener {
            showFileChooser()

        }
        front_pic.setOnClickListener {
            val camera_intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(camera_intent, pic_one)
        }
        back_pic.setOnClickListener {
            val camera_intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(camera_intent, pic_two)
        }
        submit_btn.setOnClickListener {
            val nicnum : String = nicnum.text.toString()
            val phonenum : String = phonenum.text.toString()
            val pdf : String = tv_pdfname.text.toString()
            val myObj =
                basic(front = photo1.toString(), back = photo2.toString(), nic_num = nicnum, phone_num = phonenum, pdf = pdf)
            database.dao().insertBasic(myObj)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == pic_one && resultCode == RESULT_OK && data != null) {

            // BitMap is data structure of image file which store the image in memory
            photo1 = (data!!.extras!!["data"] as Bitmap?)!!
            val myphotoBitmap = compressBitmap(photo1!!,100)
            front_pic.setImageBitmap(myphotoBitmap)

        } else if (requestCode == pic_two && resultCode == RESULT_OK && data != null) {

            // BitMap is data structure of image file which store the image in memory
            photo2 = (data!!.extras!!["data"] as Bitmap?)!!
            val myphotoBitmap = compressBitmap(photo2!!,10)
            back_pic.setImageBitmap(myphotoBitmap)


        } else if (requestCode == pdf_Code && resultCode == RESULT_OK && data != null) {

            val uri: Uri? = data.data
            val path: String = uri?.path.toString()
            val file = File(path)
            tv_pdfname.text = file.name
        }

    }
    fun compressBitmap(inputBitmap: Bitmap, quality: Int): Bitmap {
        val outputStream = ByteArrayOutputStream()
        inputBitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
        val byteArray = outputStream.toByteArray()
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }


    fun showFileChooser() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "application/pdf"
//        intent.addCategory(Intent.CATEGORY_OPENABLE)
        try {
            startActivityForResult(Intent.createChooser(intent, ""), pdf_Code)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Try Again!", Toast.LENGTH_SHORT).show()
        }
    }

}
