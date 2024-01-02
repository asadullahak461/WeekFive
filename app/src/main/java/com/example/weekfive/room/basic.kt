package com.example.weekfive.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class basic(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val front: String,
    val back: String,
    val nic_num: String,
    val phone_num: String,
    val pdf : String
)
