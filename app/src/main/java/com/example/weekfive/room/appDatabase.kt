package com.example.weekfive.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities =[maleData::class,femaleData::class], version = 1 )
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): dao

}




