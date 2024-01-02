package com.example.weekfive.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class advance(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val pic1: String,
    val pic2: String,
    val pic3: String,
    val pic4: String,
    val pdf: String,
    val city: String
)
