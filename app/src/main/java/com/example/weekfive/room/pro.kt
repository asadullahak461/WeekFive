package com.example.weekfive.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class pro(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val pic: String,
    val time: String,
    val city: String,
)
