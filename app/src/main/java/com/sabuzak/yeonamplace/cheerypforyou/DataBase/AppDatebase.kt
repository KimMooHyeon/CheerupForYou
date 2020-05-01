package com.sabuzak.yeonamplace.cheerypforyou.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sabuzak.yeonamplace.cheerypforyou.DataBase.Dao.BannerDao
import com.sabuzak.yeonamplace.cheerypforyou.DataBase.Entity.Banner

@Database(entities = arrayOf(Banner::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun  bannerDao(): BannerDao

}