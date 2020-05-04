package com.sabuzak.yeonamplace.cheerupforyou.DataBase.Dao

import androidx.room.*
import com.sabuzak.yeonamplace.cheerupforyou.DataBase.Entity.Banner

@Dao
interface BannerDao {
    @Insert
    suspend fun create(banner: Banner):Long
    @Query("SELECT * FROM BANNER_TB")
    fun getAll(): List<Banner>

    @Query("SELECT * FROM BANNER_TB WHERE idx = :idx")
    fun getBannerByIdx(idx:Int):Banner

    @Delete
    suspend fun delete(banner: Banner)

    @Update
    suspend fun update(banner: Banner)

}