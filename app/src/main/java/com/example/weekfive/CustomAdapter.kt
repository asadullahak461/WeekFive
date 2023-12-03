package com.example.weekfive

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.weekfive.activities.AdvanceFormActivity
import com.example.weekfive.activities.FemaleActivity
import com.example.weekfive.activities.MaleActivity
import com.example.weekfive.room.AppDatabase
import com.example.weekfive.room.femaleData
import com.example.weekfive.room.maleData
import com.google.android.material.imageview.ShapeableImageView
import kotlin.math.log

class CustomAdapter(private val itemslist: List<items>,private val context: Context,
                    private  val database: AppDatabase) :
    RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.list_items, parent,
            false
        )
        return MyViewHolder(view)

    }

    override fun getItemCount(): Int {
        return itemslist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemslist[position]





        holder.itemView.setOnClickListener {
            try {


            if (position == 0) {
                val intent = Intent(holder.itemView.context, MaleActivity::class.java)
                holder.itemView.context.startActivity(intent)
            } else if (position == 1) {
                val intent = Intent(holder.itemView.context, FemaleActivity::class.java)
                holder.itemView.context.startActivity(intent)
            } else {
                val maleList: List<maleData> = database.dao().getAllmaleData()
                val femaleList: List<femaleData> = database.dao().getAllfemaleData()
                if (maleList.size > 0 || femaleList.size > 0) {
                    val intent = Intent(holder.itemView.context, AdvanceFormActivity::class.java)
                    holder.itemView.context.startActivity(intent)
                } else {
                    println("Form is not filled")
                    Toast.makeText(context, "Form is not filled yet !", Toast.LENGTH_SHORT).show()

                }
            }

        }
            catch (e:Exception){
                Log.d(TAG, "onBindViewHolder: ${e.localizedMessage}")

            }            }

        holder.image.setImageResource(currentItem.image)
        holder.gender.text = currentItem.gender


    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image: ImageView = itemView.findViewById(R.id.genderimage)
        val gender: TextView = itemView.findViewById(R.id.gender)

    }
}





