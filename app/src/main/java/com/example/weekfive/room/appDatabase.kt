package com.example.weekfive.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities =[maleData::class,femaleData::class,basic::class,advance::class,pro::class], version = 2 )
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): dao

}




