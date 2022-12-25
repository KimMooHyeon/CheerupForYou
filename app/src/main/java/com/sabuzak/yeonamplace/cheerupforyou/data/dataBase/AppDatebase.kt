package com.sabuzak.yeonamplace.cheerupforyou.data.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sabuzak.yeonamplace.cheerupforyou.data.dao.BannerDao
import com.sabuzak.yeonamplace.cheerupforyou.data.model.Banner

@Database(entities = [Banner::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bannerDao(): BannerDao

    companion object {
        fun create(context: Context): AppDatabase {
            val databaseBuilder =
                Room.databaseBuilder(context, AppDatabase::class.java, "banner-db")
            return databaseBuilder.fallbackToDestructiveMigration().build()
        }
    }
}