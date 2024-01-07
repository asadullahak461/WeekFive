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

    @Insert
    fun insertBasic(basic: basic)

    @Insert
    fun insertAdvance(advance: advance)

    @Insert
    fun insertPro(pro: pro)

    @Query("SELECT COUNT(*) FROM maleData")
    fun getMaleCount():Int

    @Query("SELECT COUNT(*) FROM femaleData")
    fun getFemaleCount():Int

    @Query("SELECT COUNT(*) FROM basic")
    fun getBasicCount():Int


    @Query("SELECT COUNT(*) FROM advance")
    fun getAdvanceCount():Int


    @Query("SELECT COUNT(*) FROM pro")
    fun getProCount():Int





}