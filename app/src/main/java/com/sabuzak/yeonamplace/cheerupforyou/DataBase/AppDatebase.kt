package com.sabuzak.yeonamplace.cheerupforyou.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sabuzak.yeonamplace.cheerupforyou.DataBase.Dao.BannerDao
import com.sabuzak.yeonamplace.cheerupforyou.DataBase.Entity.Banner

@Database(entities = arrayOf(Banner::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun  bannerDao(): BannerDao

}