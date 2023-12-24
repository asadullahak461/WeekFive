package com.example.weekfive.fragments


import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import com.example.weekfive.R


class pro_frag : Fragment() {
    private lateinit var rootView: View
    lateinit var time_btn : Button
    lateinit var show_time : TextView
    lateinit var autoTextView : AutoCompleteTextView

    var countries = arrayOf(
        "India", "Australia", "West indies", "indonesia", "Indiana",
        "South Africa", "England", "Bangladesh", "Srilanka", "singapore"
    )

    // listener which is triggered when the
    // time is picked from the time picker dialog
    private val timePickerDialogListener: TimePickerDialog.OnTimeSetListener =
        TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute -> // logic to properly handle
            // the picked timings by user
            val formattedTime: String = when {
                hourOfDay == 0 -> {
                    if (minute < 10) {
                        "${hourOfDay + 12}:0${minute} am"
                    } else {
                        "${hourOfDay + 12}:${minute} am"
                    }
                }

                hourOfDay > 12 -> {
                    if (minute < 10) {
                        "${hourOfDay - 12}:0${minute} pm"
                    } else {
                        "${hourOfDay - 12}:${minute} pm"
                    }
                }

                hourOfDay == 12 -> {
                    if (minute < 10) {
                        "${hourOfDay}:0${minute} pm"
                    } else {
                        "${hourOfDay}:${minute} pm"
                    }
                }

                else -> {
                    if (minute < 10) {
                        "${hourOfDay}:${minute} am"
                    } else {
                        "${hourOfDay}:${minute} am"
                    }
                }
            }

            show_time.text = formattedTime
        }

            // inflate the layout
            override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View?{
       rootView=inflater.inflate(R.layout.fragment_pro, container, false)!!
        setupUi(rootView)
        return rootView
    }

    private fun setupUi(rootView: View) {
        time_btn = rootView!!.findViewById(R.id.btn_time)
        show_time = rootView!!.findViewById(R.id.show_time_txt)
        autoTextView=rootView!!.findViewById(R.id.city)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        time_btn.setOnClickListener {
            val timePicker: TimePickerDialog = TimePickerDialog(
                // pass the Context
                context,
                // listener to perform task
                // when time is picked
                timePickerDialogListener,
                // default hour when the time picker
                // dialog is opened
                12,
                // default minute when the time picker
                // dialog is opened
                10,
                // 24 hours time picker is
                // false (varies according to the region)
                false
            )

            // then after building the timepicker
            // dialog show the dialog to user
            timePicker.show()
        }
        val adapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_list_item_1, countries)
        autoTextView.setAdapter(adapter)
    }

    }

