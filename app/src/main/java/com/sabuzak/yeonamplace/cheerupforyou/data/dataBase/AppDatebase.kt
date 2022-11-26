package com.sabuzak.yeonamplace.cheerupforyou.data.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sabuzak.yeonamplace.cheerupforyou.data.dao.BannerDao
import com.sabuzak.yeonamplace.cheerupforyou.data.model.Banner

@Database(entities = [Banner::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

     abstract fun  bannerDao(): BannerDao
    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstacne = INSTANCE
            if (tempInstacne != null) {
                return tempInstacne
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "banner-db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}