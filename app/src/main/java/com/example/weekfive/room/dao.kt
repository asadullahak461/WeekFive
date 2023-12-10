package com.example.weekfive.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface dao {

    @Insert
    fun insertMale(maleData: maleData)

    @Query("SELECT * FROM maleData")
    fun getAllmaleData(): List<maleData>



    @Insert
    fun insertFemale(femaleData: femaleData)

    //read getAllfemaleData
    @Query("SELECT * FROM femaleData")
    fun getAllfemaleData(): List<femaleData>
}