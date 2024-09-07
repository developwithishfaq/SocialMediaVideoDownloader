package com.test.downloadmanager.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.downloadmanager.data.model.InProgressVideo

@Database(entities = [InProgressVideo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun inProgressDao(): InProgressDao

}