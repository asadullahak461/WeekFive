package com.example.weekfive

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weekfive.activities.FemaleActivity
import com.example.weekfive.activities.MaleActivity
import com.google.android.material.imageview.ShapeableImageView

class CustomAdapter(private val itemslist : List<items>) :
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
            if (position == 0) {
                val intent = Intent(holder.itemView.context, MaleActivity::class.java)
                holder.itemView.context.startActivity(intent)
            } else {
                val intent = Intent(holder.itemView.context, FemaleActivity::class.java)
                holder.itemView.context.startActivity(intent)
            }
        }

            holder.image.setImageResource(currentItem.image)
            holder.gender.text = currentItem.gender


        }



        class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val image: ImageView = itemView.findViewById(R.id.genderimage)
            val gender: TextView = itemView.findViewById(R.id.gender)

        }
    }





