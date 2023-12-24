package com.example.weekfive.fragments

import android.R.attr
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.hardware.camera2.CameraCharacteristics
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.weekfive.R
import java.io.File


class basic_frag : Fragment() {
    private lateinit var rootView: View
    lateinit var upload_btn: Button
    lateinit var tv_pdfname: TextView
    lateinit var front_pic: ImageView
    lateinit var back_pic: ImageView
    private val pic_one = 1
    private val pic_two = 2
    private val pdf_Code = 3


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
        upload_btn = rootView!!.findViewById(R.id.btnpdf)
        tv_pdfname = rootView!!.findViewById(R.id.txtpdf)
        front_pic = rootView!!.findViewById(R.id.front_pic)
        back_pic = rootView!!.findViewById(R.id.back_pic)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        upload_btn.setOnClickListener {
            showFileChooser()

        }
        front_pic.setOnClickListener {
            val camera_intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            camera_intent.putExtra("android.intent.extras.CAMERA_FACING", 1)
            startActivityForResult(camera_intent, pic_one)
        }
        back_pic.setOnClickListener {
            val camera_intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            startActivityForResult(camera_intent, pic_two)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == pic_one && resultCode == RESULT_OK && data != null) {

            // BitMap is data structure of image file which store the image in memory
            val photo = data!!.extras!!["data"] as Bitmap?
            front_pic.setImageBitmap(photo)

        } else if (requestCode == pic_two && resultCode == RESULT_OK && data != null) {

            // BitMap is data structure of image file which store the image in memory
            val photo = data!!.extras!!["data"] as Bitmap?
            back_pic.setImageBitmap(photo)

        } else if (requestCode == pdf_Code && resultCode == RESULT_OK && data != null) {

            val uri: Uri? = data.data
            val path: String = uri?.path.toString()
            val file = File(path)
            tv_pdfname.text = file.name
        }
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
