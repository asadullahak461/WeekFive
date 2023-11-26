package com.example.weekfive.room

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface dao {

    @Insert
    fun insertMale(maleData: maleData)

    @Insert
    fun insertFemale(femaleData: femaleData)

}