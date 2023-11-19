package com.example.weekfive.room

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["name"], unique = true)])
data class maleData(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val image: String,
    val name: String,
    val email: String,
    val password: String,
    val aboveEighteen: Boolean,
    val city: String,
    val dob: String


)
